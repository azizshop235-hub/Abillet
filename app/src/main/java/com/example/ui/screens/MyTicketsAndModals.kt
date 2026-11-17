package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.rounded.Business
import androidx.compose.material.icons.rounded.ConfirmationNumber
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.Hotel
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.QrCode2
import androidx.compose.material.icons.rounded.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.AbilletSampleData
import com.example.model.BookedTicket
import com.example.model.Currency
import com.example.ui.theme.AbilletAmber
import com.example.ui.theme.AbilletAmberContainer
import com.example.ui.theme.AbilletBorder
import com.example.ui.theme.AbilletNavy
import com.example.ui.theme.AbilletNavyContainer
import com.example.ui.theme.AbilletNavyDark
import com.example.ui.theme.AbilletSlateBg
import com.example.ui.theme.AbilletTextMuted
import com.example.ui.theme.AbilletTextSecondary
import com.example.ui.theme.EmeraldContainer
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.WhatsAppGreen
import com.example.ui.theme.WhatsAppGreenBg
import com.example.viewmodel.CityPickerTarget

@Composable
fun MyTicketsScreen(
    tickets: List<BookedTicket>,
    currency: Currency,
    onTicketClick: (BookedTicket) -> Unit,
    onBookNewClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AbilletSlateBg)
    ) {
        Surface(color = AbilletNavy, shadowElevation = 3.dp) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 14.dp)
            ) {
                Text(
                    text = "Mes billets",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
                Text(
                    text = "${tickets.size} réservation(s) active(s) avec QR Code",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.78f)
                )
            }
        }

        if (tickets.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ConfirmationNumber,
                        contentDescription = null,
                        tint = AbilletTextMuted,
                        modifier = Modifier.size(56.dp)
                    )
                    Text(
                        text = "Aucun billet pour le moment",
                        style = MaterialTheme.typography.titleMedium,
                        color = AbilletNavy
                    )
                    Button(
                        onClick = onBookNewClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AbilletAmber,
                            contentColor = AbilletNavyDark
                        )
                    ) {
                        Text("Rechercher un trajet")
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tickets, key = { it.ticketCode }) { ticket ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("saved_ticket_${ticket.ticketCode}")
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { onTicketClick(ticket) },
                        shape = RoundedCornerShape(14.dp),
                        color = Color.White,
                        shadowElevation = 2.dp,
                        border = BorderStroke(1.dp, AbilletBorder)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(36.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(AbilletNavyContainer),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = if (ticket.isHotelBooking) Icons.Rounded.Hotel else Icons.Rounded.DirectionsBus,
                                            contentDescription = null,
                                            tint = AbilletNavy,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                    Column {
                                        Text(
                                            text = ticket.companyName ?: ticket.hotelName.orEmpty(),
                                            style = MaterialTheme.typography.titleMedium,
                                            color = AbilletNavy
                                        )
                                        Text(
                                            text = ticket.travelDate,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = AbilletTextSecondary
                                        )
                                    }
                                }

                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = EmeraldContainer
                                ) {
                                    Text(
                                        text = ticket.ticketCode,
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                        color = EmeraldSuccess,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }

                            HorizontalDivider(color = AbilletBorder)

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (!ticket.isHotelBooking) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Text(
                                            text = ticket.departureCity.orEmpty(),
                                            style = MaterialTheme.typography.titleSmall,
                                            color = AbilletNavy
                                        )
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                                            contentDescription = null,
                                            tint = AbilletAmber,
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Text(
                                            text = ticket.arrivalCity.orEmpty(),
                                            style = MaterialTheme.typography.titleSmall,
                                            color = AbilletNavy
                                        )
                                        Text(
                                            text = "• Siège ${ticket.seatNumber ?: 14}",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = EmeraldSuccess
                                        )
                                    }
                                } else {
                                    Text(
                                        text = "${ticket.hotelCity} • ${ticket.hotelNights ?: 1} nuit(s)",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = AbilletNavy
                                    )
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.QrCode2,
                                        contentDescription = null,
                                        tint = AbilletNavy,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Text(
                                        text = currency.format(ticket.totalPriceXaf),
                                        style = MaterialTheme.typography.labelLarge,
                                        color = AbilletNavy
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityPickerBottomSheet(
    target: CityPickerTarget,
    selectedDeparture: String,
    selectedArrival: String,
    onCitySelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val title = when (target) {
        CityPickerTarget.DEPARTURE -> "Choisir la ville de départ"
        CityPickerTarget.ARRIVAL -> "Choisir la ville d'arrivée"
        CityPickerTarget.HOTEL_CITY -> "Choisir la destination hôtel"
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = AbilletNavy
            )
            LazyColumn(
                modifier = Modifier.height(340.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(AbilletSampleData.chadCities, key = { it.name }) { city ->
                    val isCurrent = when (target) {
                        CityPickerTarget.DEPARTURE -> city.name == selectedDeparture
                        CityPickerTarget.ARRIVAL -> city.name == selectedArrival
                        CityPickerTarget.HOTEL_CITY -> false
                    }
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("pick_city_${city.name}")
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { onCitySelected(city.name) }
                            .minimumInteractiveComponentSize(),
                        shape = RoundedCornerShape(12.dp),
                        color = if (isCurrent) AbilletNavyContainer else AbilletSlateBg,
                        border = BorderStroke(1.dp, if (isCurrent) AbilletNavy else AbilletBorder)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.LocationOn,
                                contentDescription = null,
                                tint = if (isCurrent) AbilletAmber else AbilletNavy
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = city.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = AbilletNavy
                                )
                                Text(
                                    text = "${city.region} • ${city.mainStation}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = AbilletTextSecondary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportBottomSheet(
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(WhatsAppGreenBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.SupportAgent,
                        contentDescription = null,
                        tint = WhatsAppGreen
                    )
                }
                Column {
                    Text(
                        text = "Assistance Abillet Tchad 7j/7",
                        style = MaterialTheme.typography.titleLarge,
                        color = AbilletNavy
                    )
                    Text(
                        text = "Réponse moyenne en moins de 3 minutes",
                        style = MaterialTheme.typography.bodySmall,
                        color = WhatsAppGreen
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = AbilletSlateBg,
                border = BorderStroke(1.dp, AbilletBorder)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Rounded.Phone, null, tint = AbilletNavy, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Ligne directe & WhatsApp : +235 66 00 24 24", style = MaterialTheme.typography.titleSmall, color = AbilletNavy)
                    }
                    Text(
                        text = "Modification de billet gratuite jusqu'à 2h avant le départ auprès du chef de gare ou via WhatsApp.",
                        style = MaterialTheme.typography.bodySmall,
                        color = AbilletTextSecondary
                    )
                }
            }

            Button(
                onClick = onDismiss,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = WhatsAppGreen,
                    contentColor = Color.White
                )
            ) {
                Text("Contacter le support WhatsApp", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgencyAccountBottomSheet(
    loggedInLabel: String?,
    onSelectAccount: (String) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Business,
                    contentDescription = null,
                    tint = AbilletNavy,
                    modifier = Modifier.size(28.dp)
                )
                Column {
                    Text(
                        text = if (loggedInLabel != null) "Connecté : $loggedInLabel" else "Espace Voyageur & Agence",
                        style = MaterialTheme.typography.titleLarge,
                        color = AbilletNavy
                    )
                    Text(
                        text = "Réseau officiel des compagnies de bus du Tchad",
                        style = MaterialTheme.typography.bodySmall,
                        color = AbilletTextSecondary
                    )
                }
            }

            Text(
                text = "Compagnies partenaires connectées :",
                style = MaterialTheme.typography.labelMedium,
                color = AbilletTextSecondary
            )

            AbilletSampleData.agencyPartners.forEach { partner ->
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = AbilletSlateBg,
                    border = BorderStroke(1.dp, AbilletBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onSelectAccount(partner.name) }
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(partner.name, style = MaterialTheme.typography.titleSmall, color = AbilletNavy)
                            Text("${partner.mainHub} • ${partner.fleetType}", style = MaterialTheme.typography.bodySmall, color = AbilletTextSecondary)
                        }
                        Surface(shape = RoundedCornerShape(8.dp), color = AbilletAmberContainer) {
                            Text(
                                text = "${partner.routesCount} lignes",
                                style = MaterialTheme.typography.labelSmall,
                                color = AbilletNavyDark,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }

            Button(
                onClick = { onSelectAccount("Mahamat Moussa (Voyageur)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AbilletNavy,
                    contentColor = Color.White
                )
            ) {
                Icon(Icons.Rounded.Verified, null, tint = AbilletAmber, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Connexion rapide Voyageur (+235)", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}
