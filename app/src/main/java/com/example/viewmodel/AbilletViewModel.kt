package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AbilletSampleData
import com.example.model.BookedTicket
import com.example.model.BusFilterOption
import com.example.model.BusTrip
import com.example.model.Currency
import com.example.model.HotelItem
import com.example.model.PaymentMethod
import com.example.model.PopularRoute
import com.example.model.QuickDateOption
import com.example.model.SearchTab
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

enum class CityPickerTarget {
    DEPARTURE,
    ARRIVAL,
    HOTEL_CITY
}

data class AbilletUiState(
    val currency: Currency = Currency.XAF,
    val activeSearchTab: SearchTab = SearchTab.BUS,
    val departureCity: String = "N'Djamena",
    val arrivalCity: String = "Moundou",
    val selectedDate: QuickDateOption = QuickDateOption.TODAY,
    val hotelCity: String = "N'Djamena",
    val hotelNights: Int = 2,
    val hotelGuests: Int = 2,
    // Results & Selection
    val busResults: List<BusTrip> = AbilletSampleData.generateTripsForRoute("N'Djamena", "Moundou"),
    val hotelResults: List<HotelItem> = AbilletSampleData.recommendedHotels,
    val activeBusFilter: BusFilterOption = BusFilterOption.ALL,
    val expandedTripId: String? = null,
    val selectedTrip: BusTrip? = null,
    val selectedSeatNumber: Int? = null,
    val selectedHotel: HotelItem? = null,
    // Checkout & Ticket
    val passengerName: String = "Mahamat Moussa",
    val passengerPhone: String = "+235 66 42 19 08",
    val selectedPaymentMethod: PaymentMethod = PaymentMethod.AIRTEL_MONEY,
    val isProcessingPayment: Boolean = false,
    val activeTicketPreview: BookedTicket? = null,
    val bookedTickets: List<BookedTicket> = listOf(AbilletSampleData.initialDemoTicket),
    // Modals & Interactive Helpers
    val cityPickerTarget: CityPickerTarget? = null,
    val showSupportSheet: Boolean = false,
    val showAgencySheet: Boolean = false,
    val agencyLoggedInName: String? = null
)

class AbilletViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AbilletUiState())
    val uiState: StateFlow<AbilletUiState> = _uiState.asStateFlow()

    fun setCurrency(currency: Currency) {
        _uiState.update { it.copy(currency = currency) }
    }

    fun setSearchTab(tab: SearchTab) {
        _uiState.update { it.copy(activeSearchTab = tab) }
    }

    fun swapBusCities() {
        _uiState.update { state ->
            state.copy(
                departureCity = state.arrivalCity,
                arrivalCity = state.departureCity
            )
        }
    }

    fun selectQuickDate(dateOption: QuickDateOption) {
        _uiState.update { it.copy(selectedDate = dateOption) }
    }

    fun openCityPicker(target: CityPickerTarget) {
        _uiState.update { it.copy(cityPickerTarget = target) }
    }

    fun dismissCityPicker() {
        _uiState.update { it.copy(cityPickerTarget = null) }
    }

    fun selectCity(cityName: String) {
        _uiState.update { state ->
            when (state.cityPickerTarget) {
                CityPickerTarget.DEPARTURE -> {
                    val newArrival = if (cityName == state.arrivalCity) state.departureCity else state.arrivalCity
                    state.copy(
                        departureCity = cityName,
                        arrivalCity = newArrival,
                        cityPickerTarget = null
                    )
                }
                CityPickerTarget.ARRIVAL -> {
                    val newDeparture = if (cityName == state.departureCity) state.arrivalCity else state.departureCity
                    state.copy(
                        arrivalCity = cityName,
                        departureCity = newDeparture,
                        cityPickerTarget = null
                    )
                }
                CityPickerTarget.HOTEL_CITY -> {
                    state.copy(
                        hotelCity = cityName,
                        cityPickerTarget = null
                    )
                }
                null -> state
            }
        }
    }

    fun adjustHotelNights(delta: Int) {
        _uiState.update { state ->
            state.copy(hotelNights = (state.hotelNights + delta).coerceIn(1, 14))
        }
    }

    fun adjustHotelGuests(delta: Int) {
        _uiState.update { state ->
            state.copy(hotelGuests = (state.hotelGuests + delta).coerceIn(1, 6))
        }
    }

    fun searchBuses(fromCity: String? = null, toCity: String? = null) {
        _uiState.update { state ->
            val from = fromCity ?: state.departureCity
            val to = toCity ?: state.arrivalCity
            val trips = AbilletSampleData.generateTripsForRoute(from, to)
            state.copy(
                activeSearchTab = SearchTab.BUS,
                departureCity = from,
                arrivalCity = to,
                busResults = trips,
                activeBusFilter = BusFilterOption.ALL,
                expandedTripId = trips.firstOrNull()?.id,
                selectedTrip = trips.firstOrNull(),
                selectedSeatNumber = null,
                selectedHotel = null
            )
        }
    }

    fun searchPopularRoute(route: PopularRoute) {
        searchBuses(fromCity = route.fromCity, toCity = route.toCity)
    }

    fun searchHotels(cityOverride: String? = null) {
        _uiState.update { state ->
            val targetCity = cityOverride ?: state.hotelCity
            val matching = AbilletSampleData.recommendedHotels.filter {
                it.city.equals(targetCity, ignoreCase = true)
            }
            val orderedResults = if (matching.isNotEmpty()) {
                matching + AbilletSampleData.recommendedHotels.filterNot { it in matching }
            } else {
                AbilletSampleData.recommendedHotels
            }
            state.copy(
                activeSearchTab = SearchTab.HOTEL,
                hotelCity = targetCity,
                hotelResults = orderedResults,
                selectedHotel = null
            )
        }
    }

    fun setBusFilter(filter: BusFilterOption) {
        _uiState.update { it.copy(activeBusFilter = filter) }
    }

    fun toggleTripSeatMap(trip: BusTrip) {
        _uiState.update { state ->
            val isSame = state.expandedTripId == trip.id
            if (isSame) {
                state.copy(expandedTripId = null)
            } else {
                state.copy(
                    expandedTripId = trip.id,
                    selectedTrip = trip,
                    selectedSeatNumber = if (state.selectedTrip?.id == trip.id) state.selectedSeatNumber else null
                )
            }
        }
    }

    fun selectSeat(trip: BusTrip, seatNumber: Int) {
        if (seatNumber in trip.occupiedSeats) return
        _uiState.update { state ->
            val nextSeat = if (state.selectedTrip?.id == trip.id && state.selectedSeatNumber == seatNumber) {
                null
            } else {
                seatNumber
            }
            state.copy(
                selectedTrip = trip,
                selectedSeatNumber = nextSeat,
                selectedHotel = null
            )
        }
    }

    fun selectHotelForBooking(hotel: HotelItem) {
        _uiState.update { state ->
            state.copy(
                selectedHotel = hotel,
                selectedTrip = null,
                selectedSeatNumber = null
            )
        }
    }

    fun updatePassengerName(name: String) {
        _uiState.update { it.copy(passengerName = name) }
    }

    fun updatePassengerPhone(phone: String) {
        _uiState.update { it.copy(passengerPhone = phone) }
    }

    fun selectPaymentMethod(method: PaymentMethod) {
        _uiState.update { it.copy(selectedPaymentMethod = method) }
    }

    fun clearActiveTicketPreview() {
        _uiState.update { it.copy(activeTicketPreview = null) }
    }

    fun openTicketPreview(ticket: BookedTicket) {
        _uiState.update { it.copy(activeTicketPreview = ticket) }
    }

    fun confirmPaymentAndGenerateTicket(onCompleted: () -> Unit) {
        val currentState = _uiState.value
        if (currentState.isProcessingPayment) return

        viewModelScope.launch {
            _uiState.update { it.copy(isProcessingPayment = true) }
            delay(850)

            val codeNum = Random.nextInt(1000, 9999)
            val generatedCode = "AB-$codeNum-TD"
            val name = currentState.passengerName.trim().ifEmpty { "Voyageur Abillet" }
            val phone = currentState.passengerPhone.trim().ifEmpty { "+235 66 00 00 00" }

            val newTicket = if (currentState.selectedHotel != null) {
                val hotel = currentState.selectedHotel
                BookedTicket(
                    ticketCode = generatedCode,
                    passengerName = name,
                    phoneNumber = phone,
                    travelDate = "${currentState.selectedDate.displayDate} • ${currentState.hotelNights} nuit(s)",
                    paymentMethod = currentState.selectedPaymentMethod,
                    totalPriceXaf = hotel.pricePerNightXaf * currentState.hotelNights,
                    isHotelBooking = true,
                    hotelName = hotel.name,
                    hotelCity = hotel.city,
                    hotelNights = currentState.hotelNights,
                    hotelAddress = hotel.neighborhood
                )
            } else {
                val trip = currentState.selectedTrip ?: currentState.busResults.first()
                val seat = currentState.selectedSeatNumber ?: (1..50).first { it !in trip.occupiedSeats }
                BookedTicket(
                    ticketCode = generatedCode,
                    passengerName = name,
                    phoneNumber = phone,
                    travelDate = currentState.selectedDate.displayDate,
                    paymentMethod = currentState.selectedPaymentMethod,
                    totalPriceXaf = trip.priceXaf,
                    isHotelBooking = false,
                    companyName = trip.companyName,
                    departureCity = trip.departureCity,
                    arrivalCity = trip.arrivalCity,
                    departureTime = trip.departureTime,
                    duration = trip.duration,
                    boardingStation = trip.departureStation,
                    seatNumber = seat,
                    busType = trip.busType
                )
            }

            _uiState.update { state ->
                state.copy(
                    isProcessingPayment = false,
                    activeTicketPreview = newTicket,
                    bookedTickets = listOf(newTicket) + state.bookedTickets
                )
            }
            onCompleted()
        }
    }

    fun setShowSupportSheet(show: Boolean) {
        _uiState.update { it.copy(showSupportSheet = show) }
    }

    fun setShowAgencySheet(show: Boolean) {
        _uiState.update { it.copy(showAgencySheet = show) }
    }

    fun signInAgencyOrTraveler(accountLabel: String) {
        _uiState.update { it.copy(agencyLoggedInName = accountLabel, showAgencySheet = false) }
    }
}
