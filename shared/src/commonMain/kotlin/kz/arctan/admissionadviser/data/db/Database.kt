package kz.arctan.admissionadviser.data.db

import app.cash.sqldelight.db.SqlDriver
import kz.arctan.Database

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()
    return Database(driver)
}