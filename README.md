# EV Logbook

A simple, offline-first Android app for tracking electric vehicle usage and charging costs.

## Features

- **Trip Tracking**: Log your EV trips with start/end odometer readings and optional energy consumption
- **Charging Sessions**: Record charging sessions with energy amount, unit price, and location
- **Dashboard**: View monthly statistics including distance, energy usage, costs, and efficiency
- **Reports**: Monthly reports with daily breakdowns and charts
- **Data Export/Import**: Export trips and charges to CSV for backup or analysis
- **Settings**: Configure currency, electricity rates, and efficiency defaults
- **Privacy First**: All data stored locally, no network calls or third-party services

## Screenshots

*Add screenshots here once available*

## Installation

### Requirements

- Android 8.0 (API level 24) or higher
- Kotlin 1.9+
- Android Studio Iguana or later

### Build Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/ev-logbook.git
   cd ev-logbook
   ```

2. Open the project in Android Studio

3. Sync Gradle files and build the project

4. Run on device or emulator

## Usage

### Adding a Trip

1. Tap the "Add Trip" FAB on the home screen
2. Enter start and end odometer readings
3. Optionally add energy consumption (auto-calculated if left blank)
4. Add notes if needed
5. Save the trip

### Adding a Charge

1. Tap the "Add Charge" FAB on the home screen
2. Enter energy amount in kWh
3. Set unit price (uses default from settings)
4. Add location and notes if needed
5. Save the charge

### Viewing Reports

1. Navigate to Reports from the bottom navigation
2. Select month to view
3. See daily breakdowns and charts

### Export/Import Data

1. Go to Reports screen
2. Tap Export CSV to backup your data
3. Tap Import CSV to restore from backup

## CSV Format

### Trips CSV (`trips.csv`)

```csv
id,dateTimeISO,startOdoKm,endOdoKm,distanceKm,energyKWh,notes
1,2024-01-15T10:30:00Z,100.0,150.0,50.0,7.0,Trip to work
2,2024-01-16T14:20:00Z,150.0,200.0,50.0,,Weekend drive
```

### Charges CSV (`charges.csv`)

```csv
id,dateTimeISO,energyKWh,unitPrice,cost,location,notes
1,2024-01-15T20:00:00Z,45.0,62.0,2790.0,Home,Overnight charge
2,2024-01-16T16:30:00Z,30.0,62.0,1860.0,Office,Workplace charging
```

## Configuration

### Default Settings

- **Currency**: LKR (configurable in Settings)
- **Electricity Unit Price**: 62.0 LKR/kWh (configurable)
- **Default Efficiency**: 14.0 kWh/100km (configurable)

### Changing Settings

1. Go to Settings
2. Update currency code, unit price, or efficiency
3. Tap Save

## Architecture

This app follows Clean Architecture principles with:

- **Data Layer**: Room database, DAOs, repositories
- **Domain Layer**: Use cases for business logic
- **UI Layer**: Jetpack Compose screens and ViewModels
- **Dependency Injection**: Hilt for DI

## Technology Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material 3
- **Database**: Room (SQLite)
- **Dependency Injection**: Hilt
- **Concurrency**: Kotlin Coroutines + Flows
- **Navigation**: Navigation Compose
- **Charts**: Custom Compose canvas implementation
- **CSV Processing**: OpenCSV
- **Testing**: JUnit 5, Turbine, MockK, Robolectric

## Privacy

EV Logbook respects your privacy:

- All data is stored locally on your device
- No network calls or data transmission
- No analytics or tracking
- No third-party services

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Run `./gradlew test` and `./gradlew lint`
6. Submit a pull request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Troubleshooting

### Common Issues

1. **Build fails**: Ensure you have Java 17 and Android SDK 34 installed
2. **App crashes on startup**: Check that all dependencies are properly synced
3. **Database errors**: The app uses Room's fallback to destructive migration - data may be lost on schema changes

### Getting Help

- Check the [Issues](https://github.com/yourusername/ev-logbook/issues) page
- Create a new issue with detailed information about your problem

## Changelog

### Version 1.0.0

- Initial release
- Trip and charge tracking
- Monthly reports and statistics
- CSV export/import functionality
- Configurable settings
- Material 3 design
