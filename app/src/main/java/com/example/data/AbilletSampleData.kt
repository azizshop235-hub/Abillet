package com.example.data

import com.example.R
import com.example.model.AgencyPartner
import com.example.model.BookedTicket
import com.example.model.BusTrip
import com.example.model.ChadCity
import com.example.model.HotelItem
import com.example.model.PaymentMethod
import com.example.model.PopularRoute

object AbilletSampleData {

    val chadCities = listOf(
        ChadCity("N'Djamena", "Capitale", "Gare Routière de Diguel / Chagoua"),
        ChadCity("Moundou", "Logone Occidental", "Gare Centrale de Moundou"),
        ChadCity("Abéché", "Ouaddaï", "Gare Routière d'Abéché"),
        ChadCity("Sarh", "Moyen-Chari", "Gare Centrale de Sarh"),
        ChadCity("Doba", "Logone Oriental", "Gare Routière de Doba"),
        ChadCity("Bongor", "Mayo-Kebbi Est", "Gare du Pont de Bongor"),
        ChadCity("Koumra", "Mandoul", "Gare de Koumra"),
        ChadCity("Mongo", "Guéra", "Gare Routière de Mongo"),
        ChadCity("Pala", "Mayo-Kebbi Ouest", "Gare Centrale de Pala"),
        ChadCity("Kélo", "Tandjilé", "Gare de Kélo")
    )

    val popularRoutes = listOf(
        PopularRoute(
            id = "route_ndj_mnd",
            fromCity = "N'Djamena",
            toCity = "Moundou",
            startingPriceXaf = 15000,
            estimatedDuration = "8h 30",
            dailyDepartures = 9
        ),
        PopularRoute(
            id = "route_ndj_abe",
            fromCity = "N'Djamena",
            toCity = "Abéché",
            startingPriceXaf = 20000,
            estimatedDuration = "12h 00",
            dailyDepartures = 5
        ),
        PopularRoute(
            id = "route_ndj_srh",
            fromCity = "N'Djamena",
            toCity = "Sarh",
            startingPriceXaf = 18000,
            estimatedDuration = "10h 15",
            dailyDepartures = 6
        ),
        PopularRoute(
            id = "route_ndj_dob",
            fromCity = "N'Djamena",
            toCity = "Doba",
            startingPriceXaf = 16000,
            estimatedDuration = "9h 00",
            dailyDepartures = 5
        ),
        PopularRoute(
            id = "route_ndj_bng",
            fromCity = "N'Djamena",
            toCity = "Bongor",
            startingPriceXaf = 7000,
            estimatedDuration = "4h 15",
            dailyDepartures = 11
        ),
        PopularRoute(
            id = "route_mnd_srh",
            fromCity = "Moundou",
            toCity = "Sarh",
            startingPriceXaf = 8000,
            estimatedDuration = "4h 45",
            dailyDepartures = 7
        )
    )

    val recommendedHotels = listOf(
        HotelItem(
            id = "hotel_ndj_toumai",
            name = "Hôtel La Résidence Toumaï",
            city = "N'Djamena",
            neighborhood = "Quartier Sabangali • Bord du Chari",
            rating = 4.8,
            reviewCount = 142,
            pricePerNightXaf = 45000,
            imageRes = R.drawable.img_hotel_ndjamena,
            amenities = listOf("Clim", "Wi-Fi", "Groupe électrogène", "Piscine"),
            description = "Établissement moderne au cœur de N'Djamena avec électricité continue 24h/24, navette gare et petit-déjeuner tchadien inclus.",
            checkInInfo = "Check-in dès 14:00 • Annulation gratuite"
        ),
        HotelItem(
            id = "hotel_mnd_manguiers",
            name = "Résidence Les Manguiers",
            city = "Moundou",
            neighborhood = "Quartier Dombao • Proche Gare",
            rating = 4.6,
            reviewCount = 89,
            pricePerNightXaf = 28000,
            imageRes = R.drawable.img_hotel_moundou,
            amenities = listOf("Clim", "Wi-Fi", "Groupe électrogène", "Jardin"),
            description = "Chambres calmes et climatisées au cœur d'un jardin ombragé, à 5 minutes de la gare centrale de Moundou.",
            checkInInfo = "Petit-déjeuner inclus • Paiement Mobile Money"
        ),
        HotelItem(
            id = "hotel_abe_oasis",
            name = "Hôtel Oasis du Ouaddaï",
            city = "Abéché",
            neighborhood = "Centre-ville • Avenue de la République",
            rating = 4.5,
            reviewCount = 64,
            pricePerNightXaf = 32000,
            imageRes = R.drawable.img_hotel_abeche,
            amenities = listOf("Clim", "Wi-Fi", "Groupe électrogène", "Restauration"),
            description = "Hospitalité sahélienne chaleureuse avec énergie solaire hybride, Wi-Fi haut débit et cour sécurisée.",
            checkInInfo = "Accueil 24h/24 • Parking sécurisé"
        ),
        HotelItem(
            id = "hotel_srh_chari",
            name = "Auberge du Moyen-Chari",
            city = "Sarh",
            neighborhood = "Quartier Résidentiel • Sarh",
            rating = 4.4,
            reviewCount = 51,
            pricePerNightXaf = 25000,
            imageRes = R.drawable.img_hotel_moundou,
            amenities = listOf("Clim", "Wi-Fi", "Groupe électrogène"),
            description = "Résidence confortable idéale pour les voyageurs d'affaires et les séjours en famille à Sarh.",
            checkInInfo = "Check-in rapide avec code Abillet"
        )
    )

    val agencyPartners = listOf(
        AgencyPartner("Sud Express", "Gare de Chagoua, N'Djamena", 14, 4.8, "Bus VIP 50 places"),
        AgencyPartner("Tchad Transport", "Gare Routière de Diguel", 18, 4.6, "Cars Climatisés"),
        AgencyPartner("Sahel Voyages", "Gare d'Abéché & Diguel", 11, 4.7, "Coaches Longue Distance"),
        AgencyPartner("Logone Confort", "Gare Centrale de Moundou", 9, 4.5, "Navettes Express Sud")
    )

    val initialDemoTicket = BookedTicket(
        ticketCode = "AB-8942-TD",
        passengerName = "Mahamat Moussa",
        phoneNumber = "+235 66 42 19 08",
        travelDate = "Sam. 14 Nov",
        paymentMethod = PaymentMethod.AIRTEL_MONEY,
        totalPriceXaf = 17500,
        isHotelBooking = false,
        companyName = "Sud Express",
        departureCity = "N'Djamena",
        arrivalCity = "Moundou",
        departureTime = "06:30",
        duration = "8h 30",
        boardingStation = "Gare de Chagoua • Quai 2",
        seatNumber = 14,
        busType = "Bus Climatisé VIP"
    )

    fun stationForCity(city: String): String {
        return chadCities.find { it.name.equals(city, ignoreCase = true) }?.mainStation
            ?: "Gare Centrale de $city"
    }

    fun generateTripsForRoute(fromCity: String, toCity: String): List<BusTrip> {
        val basePrice = findBasePrice(fromCity, toCity)
        val baseDuration = findDuration(fromCity, toCity)
        val fromStation = stationForCity(fromCity)
        val toStation = stationForCity(toCity)

        return listOf(
            BusTrip(
                id = "${fromCity}_${toCity}_sud_0630",
                companyName = "Sud Express",
                companyCode = "SE",
                companyAccentHex = 0xFF0F2C59,
                departureCity = fromCity,
                arrivalCity = toCity,
                departureStation = fromStation,
                arrivalStation = toStation,
                departureTime = "06:30",
                arrivalTime = computeArrival("06:30", baseDuration),
                duration = baseDuration,
                busType = "Bus Climatisé VIP",
                isVip = true,
                amenities = listOf("Climatisé", "Prises USB", "Bagage 25 kg", "Eau offerte"),
                priceXaf = basePrice + 2500,
                occupiedSeats = setOf(1, 2, 5, 6, 9, 10, 13, 17, 18, 21, 22, 26, 29, 30, 34, 38, 41, 45)
            ),
            BusTrip(
                id = "${fromCity}_${toCity}_tt_0745",
                companyName = "Tchad Transport",
                companyCode = "TT",
                companyAccentHex = 0xFFD97706,
                departureCity = fromCity,
                arrivalCity = toCity,
                departureStation = fromStation,
                arrivalStation = toStation,
                departureTime = "07:45",
                arrivalTime = computeArrival("07:45", baseDuration),
                duration = baseDuration,
                busType = "Car Confort Express",
                isVip = false,
                amenities = listOf("Climatisé", "Bagage 20 kg", "Arrêt collation"),
                priceXaf = basePrice,
                occupiedSeats = setOf(3, 4, 7, 8, 11, 15, 16, 19, 24, 25, 28, 31, 32, 35, 39, 43, 44, 47, 48)
            ),
            BusTrip(
                id = "${fromCity}_${toCity}_sv_1100",
                companyName = "Sahel Voyages",
                companyCode = "SV",
                companyAccentHex = 0xFF059669,
                departureCity = fromCity,
                arrivalCity = toCity,
                departureStation = fromStation,
                arrivalStation = toStation,
                departureTime = "11:00",
                arrivalTime = computeArrival("11:00", baseDuration),
                duration = baseDuration,
                busType = "Bus Climatisé VIP",
                isVip = true,
                amenities = listOf("Climatisé", "Wi-Fi à bord", "Prises USB", "Sièges inclinables"),
                priceXaf = basePrice + 3000,
                occupiedSeats = setOf(1, 4, 8, 12, 16, 20, 23, 27, 33, 36, 40, 42)
            ),
            BusTrip(
                id = "${fromCity}_${toCity}_lc_1530",
                companyName = "Logone Confort",
                companyCode = "LC",
                companyAccentHex = 0xFF0284C7,
                departureCity = fromCity,
                arrivalCity = toCity,
                departureStation = fromStation,
                arrivalStation = toStation,
                departureTime = "15:30",
                arrivalTime = computeArrival("15:30", baseDuration),
                duration = baseDuration,
                busType = "Car Standard",
                isVip = false,
                amenities = listOf("Ventilation", "Bagage 20 kg", "Tarif gare"),
                priceXaf = (basePrice - 1000).coerceAtLeast(5000),
                occupiedSeats = setOf(2, 3, 5, 6, 9, 10, 14, 15, 17, 18, 21, 22, 25, 26, 29, 30, 33, 34, 37, 38, 41, 45, 46, 49)
            )
        )
    }

    private fun findBasePrice(from: String, to: String): Int {
        val direct = popularRoutes.find {
            (it.fromCity.equals(from, true) && it.toCity.equals(to, true)) ||
                (it.fromCity.equals(to, true) && it.toCity.equals(from, true))
        }
        if (direct != null) return direct.startingPriceXaf
        return when {
            from == "N'Djamena" || to == "N'Djamena" -> 14000
            else -> 9500
        }
    }

    private fun findDuration(from: String, to: String): String {
        val direct = popularRoutes.find {
            (it.fromCity.equals(from, true) && it.toCity.equals(to, true)) ||
                (it.fromCity.equals(to, true) && it.toCity.equals(from, true))
        }
        return direct?.estimatedDuration ?: "6h 30"
    }

    private fun computeArrival(departure: String, duration: String): String {
        val depParts = departure.split(":")
        val depH = depParts.getOrNull(0)?.toIntOrNull() ?: 6
        val depM = depParts.getOrNull(1)?.toIntOrNull() ?: 0

        val durH = duration.substringBefore("h").trim().toIntOrNull() ?: 6
        val durM = duration.substringAfter("h").trim().toIntOrNull() ?: 0

        val totalMinutes = (depH * 60 + depM) + (durH * 60 + durM)
        val arrH = (totalMinutes / 60) % 24
        val arrM = totalMinutes % 60
        val nextDaySuffix = if (totalMinutes >= 24 * 60) " (+1j)" else ""
        return String.format("%02d:%02d%s", arrH, arrM, nextDaySuffix)
    }
}
