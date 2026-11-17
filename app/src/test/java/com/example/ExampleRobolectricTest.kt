package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.model.Currency
import com.example.viewmodel.AbilletViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `app name matches Abillet`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Abillet", appName)
    }

    @Test
    fun `swap cities and select available seat updates state`() {
        val vm = AbilletViewModel()
        assertEquals("N'Djamena", vm.uiState.value.departureCity)
        assertEquals("Moundou", vm.uiState.value.arrivalCity)

        vm.swapBusCities()
        assertEquals("Moundou", vm.uiState.value.departureCity)
        assertEquals("N'Djamena", vm.uiState.value.arrivalCity)

        vm.searchBuses()
        val trip = vm.uiState.value.busResults.first()
        val freeSeat = (1..50).first { it !in trip.occupiedSeats }
        vm.selectSeat(trip, freeSeat)
        assertEquals(freeSeat, vm.uiState.value.selectedSeatNumber)
        assertNotNull(vm.uiState.value.selectedTrip)
    }

    @Test
    fun `currency formatting outputs FCFA correctly`() {
        val formatted = Currency.XAF.format(15000)
        assertEquals("15 000 FCFA", formatted)
    }
}
