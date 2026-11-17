package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.AcUnit
import androidx.compose.material.icons.rounded.AirlineSeatReclineNormal
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.SwapHoriz
import androidx.compose.material.icons.rounded.Wifi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.BusFilterOption
import com.example.model.BusTrip
import com.example.model.Currency
import com.example.model.HotelItem
import com.example.model.SearchTab
import com.example.ui.components.InteractiveBusSeatMap
import com.example.ui.theme.AbilletAmber
import com.example.ui.theme.AbilletAmberContainer
import com.example.ui.theme.AbilletBorder
import com.example.ui.theme.AbilletNavy
import com.example.ui.theme.AbilletNavyContainer
import com.example.ui.theme.AbilletNavyDark
import com.example.ui.theme.AbilletSlateBg
import com.example.ui.theme.AbilletSurfaceVariant
import com.example.ui.theme.AbilletTextMuted
import com.example.ui.theme.AbilletTextSecondary
import com.example.ui.theme.CoralUrgency
import com.example.ui.theme.EmeraldContainer
import com.example.ui.theme.EmeraldSuccess
import com.example.viewmodel.AbilletUiState

@Composable
fun SearchResultsScreen(
    uiState: AbilletUiState,
    onBack: () -> Unit,
    onSwapAndRefresh: () -> Unit,
    onSelectBusFilter: (BusFilterOption) -> Unit,
    onToggleTripSeatMap: (BusTrip) -> Unit,
    onSelectSeat: (BusTrip, Int) -> Unit,
    onContinueToPayment: () -> Unit,
    onSelectHotel: (HotelItem) -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onBack)

    val filteredBusResults = remember(uiState.busResults, uiState.activeBusFilter) {
        when (uiState.activeBusFilter) {
            BusFilterOption.ALL -> uiState.busResults
            BusFilterOption.VIP -> uiState.busResults.filter { it.isVip }
            BusFilterOption.MORNING -> uiState.busResults.filter {
                val hour = it.departureTime.substringBefore(":").toIntOrNull() ?: 12
                hour < 12
            }
            BusFilterOption.CHEAPEST -> uiState.busResults.sortedBy { it.priceXaf }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AbilletSlateBg)
    ) {
        // Sticky Route / Hotel Summary Bar
        Surface(
            color = AbilletNavy,
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        IconButton(
                            onClick = onBack,
                            modifier = Modifier.testTag("results_back_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = "Retour à l'accueil",
                                tint = Color.White
                            )
                        }
                        Column {
                            if (uiState.activeSearchTab == SearchTab.BUS) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text(
                                        text = uiState.departureCity,
                                        style = MaterialTheme.typography.titleMedium,
                                        color = Color.White
                                    )
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                                        contentDescription = "vers",
                                        tint = AbilletAmber,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = uiState.arrivalCity,
                                        style = MaterialTheme.typography.titleMedium,
                                        color = Color.White
                                    )
                                }
                                Text(
                                    text = "${uiState.selectedDate.displayDate} • ${filteredBusResults.size} départs disponibles",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.78f)
                                )
                            } else {
                                Text(
                                    text = "Hôtels à ${uiState.hotelCity} & Tchad",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )
                                Text(
                                    text = "${uiState.selectedDate.displayDate} • ${uiState.hotelNights} nuit(s) • ${uiState.hotelGuests} voyageur(s)",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.78f)
                                )
                            }
                        }
                    }

                    if (uiState.activeSearchTab == SearchTab.BUS) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = Color.White.copy(alpha = 0.14f),
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable(onClick = onSwapAndRefresh)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.SwapHoriz,
                                    contentDescription = "Inverser le trajet",
                                    tint = AbilletAmber,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "Inverser",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }

        if (uiState.activeSearchTab == SearchTab.BUS) {
            // Bus Filters
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(BusFilterOption.entries) { filter ->
                    val selected = filter == uiState.activeBusFilter
                    FilterChip(
                        selected = selected,
                        onClick = { onSelectBusFilter(filter) },
                        label = { Text(filter.label, style = MaterialTheme.typography.labelMedium) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AbilletNavy,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredBusResults, key = { it.id }) { trip ->
                    val isExpanded = uiState.expandedTripId == trip.id
                    val selectedSeatForTrip = if (uiState.selectedTrip?.id == trip.id) {
                        uiState.selectedSeatNumber
                    } else null

                    BusTripResultCard(
                        trip = trip,
                        currency = uiState.currency,
                        isExpanded = isExpanded,
                        selectedSeat = selectedSeatForTrip,
                        onToggleSeatPicker = { onToggleTripSeatMap(trip) },
                        onSeatClick = { seat -> onSelectSeat(trip, seat) },
                        onContinueToPayment = onContinueToPayment
                    )
                }
            }
        } else {
            // Hotel Search Results
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(uiState.hotelResults, key = { it.id }) { hotel ->
                    HotelResultCard(
                        hotel = hotel,
                        nights = uiState.hotelNights,
                        currency = uiState.currency,
                        onBookClick = { onSelectHotel(hotel) }
                    )
                }
            }
        }
    }
}

@Composable
private fun BusTripResultCard(
    trip: BusTrip,
    currency: Currency,
    isExpanded: Boolean,
    selectedSeat: Int?,
    onToggleSeatPicker: () -> Unit,
    onSeatClick: (Int) -> Unit,
    onContinueToPayment: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("bus_trip_card_${trip.companyCode}"),
        shape = RoundedCornerShape(14.dp),
        color = Color.White,
        shadowElevation = if (isExpanded) 5.dp else 2.dp,
        border = BorderStroke(
            width = if (isExpanded) 1.5.dp else 1.dp,
            color = if (isExpanded) AbilletNavy else AbilletBorder
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Top Row: Company Logo Badge + Bus Type + Price
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(trip.companyAccentHex)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = trip.companyCode,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                            color = Color.White
                        )
                    }
                    Column {
                        Text(
                            text = trip.companyName,
                            style = MaterialTheme.typography.titleMedium,
                            color = AbilletNavy
                        )
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = if (trip.isVip) AbilletAmberContainer else AbilletSurfaceVariant
                        ) {
                            Text(
                                text = trip.busType,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (trip.isVip) AbilletNavyDark else AbilletTextSecondary,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = currency.format(trip.priceXaf),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                        color = AbilletNavy
                    )
                    Text(
                        text = "Tarif gare inclus",
                        style = MaterialTheme.typography.labelSmall,
                        color = EmeraldSuccess
                    )
                }
            }

            // Schedule & Route Line
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AbilletSlateBg, RoundedCornerShape(10.dp))
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = trip.departureTime,
                        style = MaterialTheme.typography.headlineSmall,
                        color = AbilletNavy
                    )
                    Text(
                        text = trip.departureCity,
                        style = MaterialTheme.typography.bodySmall,
                        color = AbilletTextSecondary
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Schedule,
                            contentDescription = null,
                            tint = AbilletTextMuted,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = trip.duration,
                            style = MaterialTheme.typography.labelSmall,
                            color = AbilletTextSecondary
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(AbilletNavy)
                        )
                        Box(
                            modifier = Modifier
                                .width(46.dp)
                                .height(1.5.dp)
                                .background(AbilletBorder)
                        )
                        Icon(
                            imageVector = Icons.Rounded.DirectionsBus,
                            contentDescription = null,
                            tint = AbilletAmber,
                            modifier = Modifier.size(16.dp)
                        )
                        Box(
                            modifier = Modifier
                                .width(46.dp)
                                .height(1.5.dp)
                                .background(AbilletBorder)
                        )
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(AbilletAmber)
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = trip.arrivalTime,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = AbilletNavy
                    )
                    Text(
                        text = trip.arrivalCity,
                        style = MaterialTheme.typography.bodySmall,
                        color = AbilletTextSecondary
                    )
                }
            }

            // Amenities + Remaining Seats + Toggle Seat Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = trip.amenities.take(3).joinToString(" • "),
                        style = MaterialTheme.typography.bodySmall,
                        color = AbilletTextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.AirlineSeatReclineNormal,
                            contentDescription = null,
                            tint = if (trip.remainingSeats <= 15) CoralUrgency else EmeraldSuccess,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(
                            text = "${trip.remainingSeats} places restantes",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = if (trip.remainingSeats <= 15) CoralUrgency else EmeraldSuccess
                        )
                    }
                }

                Button(
                    onClick = onToggleSeatPicker,
                    modifier = Modifier
                        .height(44.dp)
                        .testTag("choose_seat_button_${trip.companyCode}"),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isExpanded) AbilletNavyContainer else AbilletNavy,
                        contentColor = if (isExpanded) AbilletNavy else Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = if (isExpanded) "Masquer le plan" else "Choisir sa place",
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 13.sp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = if (isExpanded) Icons.Rounded.ExpandLess else Icons.Rounded.ExpandMore,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // Expandable Interactive 50-Seat Bus Map Accordion
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column(
                    modifier = Modifier.padding(top = 6.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    HorizontalDivider(color = AbilletBorder)
                    Text(
                        text = "Plan du bus — Sélectionnez votre siège (1 à 50)",
                        style = MaterialTheme.typography.titleSmall,
                        color = AbilletNavy
                    )
                    InteractiveBusSeatMap(
                        trip = trip,
                        selectedSeat = selectedSeat,
                        currency = currency,
                        onSeatClick = onSeatClick,
                        onContinueToPayment = onContinueToPayment
                    )
                }
            }
        }
    }
}

@Composable
private fun HotelResultCard(
    hotel: HotelItem,
    nights: Int,
    currency: Currency,
    onBookClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("hotel_result_${hotel.id}"),
        shape = RoundedCornerShape(14.dp),
        color = Color.White,
        shadowElevation = 3.dp,
        border = BorderStroke(1.dp, AbilletBorder)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(165.dp)
            ) {
                Image(
                    painter = painterResource(id = hotel.imageRes),
                    contentDescription = hotel.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = AbilletNavyDark.copy(alpha = 0.88f)
                ) {
                    Text(
                        text = hotel.city,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Star,
                            contentDescription = null,
                            tint = AbilletAmber,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(
                            text = "${hotel.rating} (${hotel.reviewCount})",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = AbilletNavy
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = hotel.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = AbilletNavy
                )
                Text(
                    text = hotel.neighborhood,
                    style = MaterialTheme.typography.bodySmall,
                    color = AbilletTextSecondary
                )
                Text(
                    text = hotel.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = AbilletTextSecondary
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Surface(shape = RoundedCornerShape(6.dp), color = AbilletSurfaceVariant) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Rounded.AcUnit, null, tint = AbilletNavy, modifier = Modifier.size(14.dp))
                            Text("Clim", style = MaterialTheme.typography.labelSmall, color = AbilletNavy)
                        }
                    }
                    Surface(shape = RoundedCornerShape(6.dp), color = AbilletSurfaceVariant) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Rounded.Wifi, null, tint = AbilletNavy, modifier = Modifier.size(14.dp))
                            Text("Wi-Fi", style = MaterialTheme.typography.labelSmall, color = AbilletNavy)
                        }
                    }
                    Surface(shape = RoundedCornerShape(6.dp), color = EmeraldContainer) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Rounded.Bolt, null, tint = EmeraldSuccess, modifier = Modifier.size(14.dp))
                            Text("Groupe 24h/24", style = MaterialTheme.typography.labelSmall, color = EmeraldSuccess)
                        }
                    }
                }

                HorizontalDivider(color = AbilletBorder)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = currency.format(hotel.pricePerNightXaf * nights),
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                            color = AbilletNavy
                        )
                        Text(
                            text = "${currency.format(hotel.pricePerNightXaf)} / nuit • $nights nuit(s)",
                            style = MaterialTheme.typography.labelSmall,
                            color = AbilletTextMuted
                        )
                    }

                    Button(
                        onClick = onBookClick,
                        modifier = Modifier.height(46.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AbilletAmber,
                            contentColor = AbilletNavyDark
                        )
                    ) {
                        Text(
                            text = "Réserver la chambre",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}
