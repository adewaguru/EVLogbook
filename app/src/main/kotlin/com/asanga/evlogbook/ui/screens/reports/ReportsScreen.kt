package com.asanga.evlogbook.ui.screens.reports

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asanga.evlogbook.R
import com.asanga.evlogbook.ui.components.BarChart
import com.asanga.evlogbook.ui.components.StatCard
import com.asanga.evlogbook.utils.Formatters
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(
    viewModel: ReportsViewModel = hiltViewModel()
) {
    val currentMonthStats by viewModel.currentMonthStats.collectAsState(initial = null)
    val currentMonthDailyStats by viewModel.currentMonthDailyStats.collectAsState(initial = emptyList())
    val snackbarHostState = remember { androidx.compose.material3.SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // Month selector state
    var selectedMonth by remember { mutableStateOf(LocalDateTime(
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year,
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).monthNumber,
        1, 0, 0, 0, 0
    )) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.reports)) }
            )
        },
        snackbarHost = { androidx.compose.material3.SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Month Selector
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = {
                    val currentDate = LocalDate(selectedMonth.year, selectedMonth.monthNumber, 1)
                    val previousMonth = currentDate.minus(1, DateTimeUnit.MONTH)
                    selectedMonth = LocalDateTime(previousMonth.year, previousMonth.monthNumber, 1, 0, 0, 0, 0)
                    // Refresh data for new month
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.previous_month))
                }

                Text(
                    text = "${selectedMonth.month.name} ${selectedMonth.year}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = {
                    val currentDate = LocalDate(selectedMonth.year, selectedMonth.monthNumber, 1)
                    val nextMonth = currentDate.plus(1, DateTimeUnit.MONTH)
                    selectedMonth = LocalDateTime(nextMonth.year, nextMonth.monthNumber, 1, 0, 0, 0, 0)
                    // Refresh data for new month
                }) {
                    Icon(Icons.Default.CalendarToday, contentDescription = stringResource(R.string.next_month))
                }
            }

            // Stats Cards
            currentMonthStats?.let { stats ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StatCard(
                        title = stringResource(R.string.total_distance),
                        value = Formatters.formatDistance(stats.totalDistance),
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = stringResource(R.string.total_energy),
                        value = Formatters.formatEnergy(stats.totalEnergy),
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StatCard(
                        title = stringResource(R.string.total_cost),
                        value = Formatters.formatCost(stats.totalCost, "LKR"),
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = stringResource(R.string.avg_consumption),
                        value = Formatters.formatEfficiency(
                            if (stats.totalDistance > 0) (stats.totalEnergy / stats.totalDistance) * 100 else 14.0
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Daily Distance Chart
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.daily_distance),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BarChart(
                        data = currentMonthDailyStats.map { it.distance },
                        labels = currentMonthDailyStats.map { it.date.substringAfterLast("-") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Daily Energy Chart
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.daily_energy),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BarChart(
                        data = currentMonthDailyStats.map { it.energy },
                        labels = currentMonthDailyStats.map { it.date.substringAfterLast("-") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Export/Import Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            // TODO: Implement CSV export
                            snackbarHostState.showSnackbar("Export functionality coming soon")
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.FileDownload, contentDescription = stringResource(R.string.export))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(stringResource(R.string.export_csv))
                }

                Button(
                    onClick = {
                        scope.launch {
                            // TODO: Implement CSV import
                            snackbarHostState.showSnackbar("Import functionality coming soon")
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.FileUpload, contentDescription = stringResource(R.string.import_data))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(stringResource(R.string.import_csv))
                }
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
