package com.example.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.rounded.AcUnit
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.Hotel
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Message
import androidx.compose.material.icons.rounded.Payments
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.SwapVert
import androidx.compose.material.icons.rounded.VerifiedUser
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
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.AbilletSampleData
import com.example.model.Currency
import com.example.model.HotelItem
import com.example.model.PopularRoute
import com.example.model.QuickDateOption
import com.example.model.SearchTab
import com.example.ui.components.AbilletBrandLogo
import com.example.ui.components.PaymentLogosStrip
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
import com.example.ui.theme.EmeraldContainer
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.WhatsAppGreen
import com.example.viewmodel.AbilletUiState
import com.example.viewmodel.CityPickerTarget

@Composable
fun HomeScreen(
    uiState: AbilletUiState,
    onTabSelected: (SearchTab) -> Unit,
    onOpenCityPicker: (CityPickerTarget) -> Unit,
    onSwapCities: () -> Unit,
    onSelectDate: (QuickDateOption) -> Unit,
    onAdjustNights: (Int) -> Unit,
    onAdjustGuests: (Int) -> Unit,
    onSearchBus: () -> Unit,
    onSearchHotel: () -> Unit,
    onSelectPopularRoute: (PopularRoute) -> Unit,
    onReserveHotel: (HotelItem) -> Unit,
    onOpenSupport: () -> Unit,
    onOpenAgency: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(AbilletSlateBg),
        contentPadding = PaddingValues(bottom = 28.dp)
    ) {
        // Hero Banner + Floating Search Card
        item {
            HeroAndSearchSection(
                uiState = uiState,
                onTabSelected = onTabSelected,
                onOpenCityPicker = onOpenCityPicker,
                onSwapCities = onSwapCities,
                onSelectDate = onSelectDate,
                onAdjustNights = onAdjustNights,
                onAdjustGuests = onAdjustGuests,
                onSearchBus = onSearchBus,
                onSearchHotel = onSearchHotel
            )
        }

        // Reassurance Banner (4 Advantages)
        item {
            ReassuranceGridSection(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }

        // Popular Routes in Chad
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Trajets populaires au Tchad",
                            style = MaterialTheme.typography.headlineSmall,
                            color = AbilletNavy
                        )
                        Text(
                            text = "Tarifs directs garantis aux guichets partenaires",
                            style = MaterialTheme.typography.bodySmall,
                            color = AbilletTextSecondary
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = AbilletAmberContainer
                    ) {
                        Text(
                            text = "Dès 7 000 F",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = AbilletNavyDark,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                PopularRoutesGrid(
                    routes = AbilletSampleData.popularRoutes,
                    currency = uiState.currency,
                    onRouteClick = onSelectPopularRoute
                )
            }
        }

        // Recommended Hotels & Residences
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Hôtels & Résidences recommandés",
                            style = MaterialTheme.typography.headlineSmall,
                            color = AbilletNavy
                        )
                        Text(
                            text = "N'Djamena, Moundou, Abéché • Clim & Groupe 24h/24",
                            style = MaterialTheme.typography.bodySmall,
                            color = AbilletTextSecondary
                        )
                    }
                }

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(AbilletSampleData.recommendedHotels.take(3), key = { it.id }) { hotel ->
                        RecommendedHotelCard(
                            hotel = hotel,
                            currency = uiState.currency,
                            onReserveClick = { onReserveHotel(hotel) }
                        )
                    }
                }
            }
        }

        // Footer Trust & Payments
        item {
            AbilletFooterSection(
                onOpenSupport = onOpenSupport,
                onOpenAgency = onOpenAgency,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 22.dp)
            )
        }
    }
}

@Composable
private fun HeroAndSearchSection(
    uiState: AbilletUiState,
    onTabSelected: (SearchTab) -> Unit,
    onOpenCityPicker: (CityPickerTarget) -> Unit,
    onSwapCities: () -> Unit,
    onSelectDate: (QuickDateOption) -> Unit,
    onAdjustNights: (Int) -> Unit,
    onAdjustGuests: (Int) -> Unit,
    onSearchBus: () -> Unit,
    onSearchHotel: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Hero Banner Image + Scrim + Headline
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_hero_chad),
                contentDescription = "Voyage en bus interurbain au Tchad",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                AbilletNavyDark.copy(alpha = 0.86f),
                                AbilletNavy.copy(alpha = 0.72f),
                                AbilletNavyDark.copy(alpha = 0.92f)
                            )
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 18.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = AbilletAmber.copy(alpha = 0.22f),
                    border = BorderStroke(1.dp, AbilletAmber.copy(alpha = 0.6f))
                ) {
                    Text(
                        text = "N°1 DE LA RÉSERVATION EN LIGNE AU TCHAD",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.8.sp
                        ),
                        color = AbilletAmber,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                Text(
                    text = "Voyagez partout au Tchad en toute simplicité.",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
                Text(
                    text = "Réservez votre bus ou votre hôtel en 2 minutes.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.88f)
                )
            }
        }

        // Floating Elevated Search Engine Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-28).dp)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 620.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 10.dp,
                border = BorderStroke(1.dp, AbilletBorder)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Segmented Tabs: [ Bus ] & [ Hôtels ]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(AbilletSurfaceVariant, RoundedCornerShape(12.dp))
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        SearchTabItem(
                            selected = uiState.activeSearchTab == SearchTab.BUS,
                            label = "Bus",
                            icon = Icons.Rounded.DirectionsBus,
                            testTag = "tab_bus",
                            onClick = { onTabSelected(SearchTab.BUS) },
                            modifier = Modifier.weight(1f)
                        )
                        SearchTabItem(
                            selected = uiState.activeSearchTab == SearchTab.HOTEL,
                            label = "Hôtels",
                            icon = Icons.Rounded.Hotel,
                            testTag = "tab_hotel",
                            onClick = { onTabSelected(SearchTab.HOTEL) },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    AnimatedContent(
                        targetState = uiState.activeSearchTab,
                        label = "searchTabContent"
                    ) { tab ->
                        if (tab == SearchTab.BUS) {
                            BusSearchForm(
                                departureCity = uiState.departureCity,
                                arrivalCity = uiState.arrivalCity,
                                selectedDate = uiState.selectedDate,
                                onDepartureClick = { onOpenCityPicker(CityPickerTarget.DEPARTURE) },
                                onArrivalClick = { onOpenCityPicker(CityPickerTarget.ARRIVAL) },
                                onSwapCities = onSwapCities,
                                onSelectDate = onSelectDate,
                                onSearchBus = onSearchBus
                            )
                        } else {
                            HotelSearchForm(
                                hotelCity = uiState.hotelCity,
                                nights = uiState.hotelNights,
                                guests = uiState.hotelGuests,
                                selectedDate = uiState.selectedDate,
                                onCityClick = { onOpenCityPicker(CityPickerTarget.HOTEL_CITY) },
                                onAdjustNights = onAdjustNights,
                                onAdjustGuests = onAdjustGuests,
                                onSearchHotel = onSearchHotel
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchTabItem(
    selected: Boolean,
    label: String,
    icon: ImageVector,
    testTag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .testTag(testTag)
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .minimumInteractiveComponentSize(),
        shape = RoundedCornerShape(10.dp),
        color = if (selected) AbilletNavy else Color.Transparent,
        shadowElevation = if (selected) 2.dp else 0.dp
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) AbilletAmber else AbilletTextSecondary,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                color = if (selected) Color.White else AbilletTextSecondary
            )
        }
    }
}

@Composable
private fun BusSearchForm(
    departureCity: String,
    arrivalCity: String,
    selectedDate: QuickDateOption,
    onDepartureClick: () -> Unit,
    onArrivalClick: () -> Unit,
    onSwapCities: () -> Unit,
    onSelectDate: (QuickDateOption) -> Unit,
    onSearchBus: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        // Departure & Arrival stacked with floating Swap button
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CitySelectorField(
                    label = "Ville de départ",
                    city = departureCity,
                    icon = Icons.Rounded.LocationOn,
                    iconTint = AbilletNavy,
                    testTag = "departure_city_field",
                    onClick = onDepartureClick
                )
                CitySelectorField(
                    label = "Ville d'arrivée",
                    city = arrivalCity,
                    icon = Icons.Rounded.LocationOn,
                    iconTint = AbilletAmber,
                    testTag = "arrival_city_field",
                    onClick = onArrivalClick
                )
            }

            Surface(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 14.dp)
                    .size(44.dp)
                    .testTag("swap_cities_button")
                    .clip(CircleShape)
                    .clickable(onClick = onSwapCities),
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 4.dp,
                border = BorderStroke(1.5.dp, AbilletNavy.copy(alpha = 0.25f))
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Rounded.SwapVert,
                        contentDescription = "Inverser départ et arrivée",
                        tint = AbilletNavy,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }

        // Date selection row
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.CalendarMonth,
                    contentDescription = null,
                    tint = AbilletTextSecondary,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "Date de départ : ${selectedDate.displayDate}",
                    style = MaterialTheme.typography.labelMedium,
                    color = AbilletTextSecondary
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                QuickDateOption.entries.forEach { option ->
                    val isSelected = option == selectedDate
                    FilterChip(
                        selected = isSelected,
                        onClick = { onSelectDate(option) },
                        label = {
                            Text(
                                text = option.label,
                                style = MaterialTheme.typography.labelMedium
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AbilletNavyContainer,
                            selectedLabelColor = AbilletNavy
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = isSelected,
                            borderColor = AbilletBorder,
                            selectedBorderColor = AbilletNavy
                        )
                    )
                }
            }
        }

        // Primary Search CTA
        Button(
            onClick = onSearchBus,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("search_bus_button"),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AbilletAmber,
                contentColor = AbilletNavyDark
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Rechercher un bus",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold)
            )
        }
    }
}

@Composable
private fun HotelSearchForm(
    hotelCity: String,
    nights: Int,
    guests: Int,
    selectedDate: QuickDateOption,
    onCityClick: () -> Unit,
    onAdjustNights: (Int) -> Unit,
    onAdjustGuests: (Int) -> Unit,
    onSearchHotel: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        CitySelectorField(
            label = "Destination au Tchad",
            city = hotelCity,
            icon = Icons.Rounded.Hotel,
            iconTint = AbilletNavy,
            testTag = "hotel_city_field",
            onClick = onCityClick
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            StepperBox(
                label = "Durée (${selectedDate.label})",
                valueText = "$nights nuit(s)",
                onDecrease = { onAdjustNights(-1) },
                onIncrease = { onAdjustNights(1) },
                modifier = Modifier.weight(1f)
            )
            StepperBox(
                label = "Voyageurs",
                valueText = "$guests pers.",
                onDecrease = { onAdjustGuests(-1) },
                onIncrease = { onAdjustGuests(1) },
                modifier = Modifier.weight(1f)
            )
        }

        Button(
            onClick = onSearchHotel,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("search_hotel_button"),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AbilletAmber,
                contentColor = AbilletNavyDark
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Rechercher un hôtel",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold)
            )
        }
    }
}

@Composable
private fun CitySelectorField(
    label: String,
    city: String,
    icon: ImageVector,
    iconTint: Color,
    testTag: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(testTag)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .minimumInteractiveComponentSize(),
        shape = RoundedCornerShape(12.dp),
        color = AbilletSlateBg,
        border = BorderStroke(1.dp, AbilletBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(22.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = AbilletTextMuted
                )
                Text(
                    text = city,
                    style = MaterialTheme.typography.titleMedium,
                    color = AbilletNavy
                )
            }
        }
    }
}

@Composable
private fun StepperBox(
    label: String,
    valueText: String,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = AbilletSlateBg,
        border = BorderStroke(1.dp, AbilletBorder)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = AbilletTextMuted,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onDecrease,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Remove,
                        contentDescription = "Diminuer",
                        tint = AbilletNavy,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Text(
                    text = valueText,
                    style = MaterialTheme.typography.titleSmall,
                    color = AbilletNavy
                )
                IconButton(
                    onClick = onIncrease,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Augmenter",
                        tint = AbilletNavy,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ReassuranceGridSection(modifier: Modifier = Modifier) {
    val items = listOf(
        Triple("Paiement Mobile Money", "Airtel Money, Moov Money, Visa/MC", Icons.Rounded.Payments),
        Triple("Billet SMS & WhatsApp", "Reçu instantanément, sans imprimer", Icons.Rounded.Message),
        Triple("Même prix qu'à la gare", "Tarifs officiels, zéro frais cachés", Icons.Rounded.VerifiedUser),
        Triple("Service client 7j/7", "Assistance téléphone & WhatsApp", Icons.Outlined.SupportAgent)
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ReassuranceCard(
                title = items[0].first,
                subtitle = items[0].second,
                icon = items[0].third,
                accentColor = AbilletAmber,
                modifier = Modifier.weight(1f)
            )
            ReassuranceCard(
                title = items[1].first,
                subtitle = items[1].second,
                icon = items[1].third,
                accentColor = WhatsAppGreen,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ReassuranceCard(
                title = items[2].first,
                subtitle = items[2].second,
                icon = items[2].third,
                accentColor = AbilletNavy,
                modifier = Modifier.weight(1f)
            )
            ReassuranceCard(
                title = items[3].first,
                subtitle = items[3].second,
                icon = items[3].third,
                accentColor = AbilletNavy,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ReassuranceCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        border = BorderStroke(1.dp, AbilletBorder)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(accentColor.copy(alpha = 0.14f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(18.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 12.sp),
                    color = AbilletNavy,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                    color = AbilletTextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun PopularRoutesGrid(
    routes: List<PopularRoute>,
    currency: Currency,
    onRouteClick: (PopularRoute) -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val columns = if (maxWidth >= 600.dp) 3 else 2
        val chunked = routes.chunked(columns)
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            chunked.forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    rowItems.forEach { route ->
                        PopularRouteCard(
                            route = route,
                            currency = currency,
                            onClick = { onRouteClick(route) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    repeat(columns - rowItems.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun PopularRouteCard(
    route: PopularRoute,
    currency: Currency,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .testTag("popular_route_${route.id}")
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .minimumInteractiveComponentSize(),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 2.dp,
        border = BorderStroke(1.dp, AbilletBorder)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = route.fromCity,
                    style = MaterialTheme.typography.titleSmall,
                    color = AbilletNavy,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                    contentDescription = "vers",
                    tint = AbilletAmber,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = route.toCity,
                    style = MaterialTheme.typography.titleSmall,
                    color = AbilletNavy,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = "${route.dailyDepartures} départs / jour • ${route.estimatedDuration}",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                color = AbilletTextMuted,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "dès ${currency.format(route.startingPriceXaf)}",
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 13.sp),
                    color = EmeraldSuccess
                )
            }
        }
    }
}

@Composable
private fun RecommendedHotelCard(
    hotel: HotelItem,
    currency: Currency,
    onReserveClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(272.dp)
            .testTag("hotel_card_${hotel.id}")
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onReserveClick),
        shape = RoundedCornerShape(14.dp),
        color = Color.White,
        shadowElevation = 3.dp,
        border = BorderStroke(1.dp, AbilletBorder)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
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
                        .padding(10.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = AbilletNavyDark.copy(alpha = 0.85f)
                ) {
                    Text(
                        text = hotel.city,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp),
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White.copy(alpha = 0.94f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Star,
                            contentDescription = null,
                            tint = AbilletAmber,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "${hotel.rating}",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = AbilletNavy
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = hotel.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = AbilletNavy,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hotel.neighborhood,
                    style = MaterialTheme.typography.bodySmall,
                    color = AbilletTextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Key Chad hotel amenities: Clim, Wi-Fi, Groupe électrogène
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HotelAmenityPill("Clim", Icons.Rounded.AcUnit)
                    HotelAmenityPill("Wi-Fi", Icons.Rounded.Wifi)
                    HotelAmenityPill("Groupe", Icons.Rounded.Bolt)
                }

                HorizontalDivider(color = AbilletBorder)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = currency.format(hotel.pricePerNightXaf),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                            color = AbilletNavy
                        )
                        Text(
                            text = "par nuit",
                            style = MaterialTheme.typography.labelSmall,
                            color = AbilletTextMuted
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = AbilletAmber
                    ) {
                        Text(
                            text = "Réserver",
                            style = MaterialTheme.typography.labelLarge,
                            color = AbilletNavyDark,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HotelAmenityPill(label: String, icon: ImageVector) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = AbilletSurfaceVariant
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AbilletNavy,
                modifier = Modifier.size(12.dp)
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                color = AbilletTextSecondary
            )
        }
    }
}

@Composable
private fun AbilletFooterSection(
    onOpenSupport: () -> Unit,
    onOpenAgency: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = AbilletNavyDark
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AbilletBrandLogo(lightText = true)
            Text(
                text = "Abillet connecte les voyageurs et les meilleures compagnies de transport routier et résidences du Tchad.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.78f)
            )

            Text(
                text = "MOYENS DE PAIEMENT ACCEPTÉS",
                style = MaterialTheme.typography.labelSmall,
                color = AbilletAmber
            )
            PaymentLogosStrip()

            HorizontalDivider(color = Color.White.copy(alpha = 0.14f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Aide WhatsApp 7j/7",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFF86EFAC),
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(onClick = onOpenSupport)
                        .padding(vertical = 6.dp, horizontal = 4.dp)
                )
                Text(
                    text = "Espace Agence Partenaire",
                    style = MaterialTheme.typography.labelMedium,
                    color = AbilletAmber,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(onClick = onOpenAgency)
                        .padding(vertical = 6.dp, horizontal = 4.dp)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White.copy(alpha = 0.1f),
                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.2f))
                ) {
                    Text(
                        text = "▶ Google Play",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White.copy(alpha = 0.1f),
                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.2f))
                ) {
                    Text(
                        text = " App Store",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}
