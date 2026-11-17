package com.example.model

import androidx.annotation.DrawableRes
import java.text.NumberFormat
import java.util.Locale

enum class Currency(
    val code: String,
    val label: String,
    val multiplierFromXaf: Double
) {
    XAF("XAF", "FCFA", 1.0),
    EUR("EUR", "€", 1.0 / 655.957),
    USD("USD", "$", 1.0 / 605.0);

    fun format(amountXaf: Int): String {
        return when (this) {
            XAF -> {
                val formatted = NumberFormat.getNumberInstance(Locale.FRANCE)
                    .format(amountXaf)
                    .replace('\u202F', ' ')
                    .replace('\u00A0', ' ')
                "$formatted FCFA"
            }
            EUR -> {
                val value = amountXaf * multiplierFromXaf
                String.format(Locale.FRANCE, "%.2f €", value)
            }
            USD -> {
                val value = amountXaf * multiplierFromXaf
                String.format(Locale.US, "$%.2f", value)
            }
        }
    }
}

enum class SearchTab(val label: String) {
    BUS("Bus"),
    HOTEL("Hôtels")
}

enum class QuickDateOption(val label: String, val displayDate: String) {
    TODAY("Aujourd'hui", "Ven. 13 Nov"),
    TOMORROW("Demain", "Sam. 14 Nov"),
    DAY_AFTER("Dimanche", "Dim. 15 Nov")
}

enum class BusFilterOption(val label: String) {
    ALL("Tous"),
    VIP("Bus VIP Climatisé"),
    MORNING("Départ matin"),
    CHEAPEST("Moins cher")
}

enum class PaymentMethod(
    val title: String,
    val subtitle: String,
    val ussdHint: String
) {
    AIRTEL_MONEY(
        title = "Airtel Money",
        subtitle = "Paiement mobile instantané Tchad",
        ussdHint = "Validation rapide via *150# ou notification push Airtel"
    ),
    MOOV_MONEY(
        title = "Moov Money",
        subtitle = "Portefeuille Moov Africa Tchad",
        ussdHint = "Confirmation sécurisée par code secret *555#"
    ),
    CARD(
        title = "Carte Bancaire",
        subtitle = "Visa • MasterCard • GIMAC",
        ussdHint = "Paiement 3D Secure sans frais supplémentaires"
    )
}

data class ChadCity(
    val name: String,
    val region: String,
    val mainStation: String
)

data class PopularRoute(
    val id: String,
    val fromCity: String,
    val toCity: String,
    val startingPriceXaf: Int,
    val estimatedDuration: String,
    val dailyDepartures: Int
)

data class BusTrip(
    val id: String,
    val companyName: String,
    val companyCode: String,
    val companyAccentHex: Long,
    val departureCity: String,
    val arrivalCity: String,
    val departureStation: String,
    val arrivalStation: String,
    val departureTime: String,
    val arrivalTime: String,
    val duration: String,
    val busType: String,
    val isVip: Boolean,
    val amenities: List<String>,
    val priceXaf: Int,
    val totalSeats: Int = 50,
    val occupiedSeats: Set<Int>
) {
    val remainingSeats: Int
        get() = (totalSeats - occupiedSeats.size).coerceAtLeast(0)
}

data class HotelItem(
    val id: String,
    val name: String,
    val city: String,
    val neighborhood: String,
    val rating: Double,
    val reviewCount: Int,
    val pricePerNightXaf: Int,
    @DrawableRes val imageRes: Int,
    val amenities: List<String>,
    val description: String,
    val checkInInfo: String
)

data class BookedTicket(
    val ticketCode: String,
    val passengerName: String,
    val phoneNumber: String,
    val travelDate: String,
    val paymentMethod: PaymentMethod,
    val totalPriceXaf: Int,
    val isHotelBooking: Boolean,
    // Bus fields
    val companyName: String? = null,
    val departureCity: String? = null,
    val arrivalCity: String? = null,
    val departureTime: String? = null,
    val duration: String? = null,
    val boardingStation: String? = null,
    val seatNumber: Int? = null,
    val busType: String? = null,
    // Hotel fields
    val hotelName: String? = null,
    val hotelCity: String? = null,
    val hotelNights: Int? = null,
    val hotelAddress: String? = null
)

data class AgencyPartner(
    val name: String,
    val mainHub: String,
    val routesCount: Int,
    val rating: Double,
    val fleetType: String
)
