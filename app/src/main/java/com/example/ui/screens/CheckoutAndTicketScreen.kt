package com.example.ui.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.Hotel
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.model.BookedTicket
import com.example.model.PaymentMethod
import com.example.ui.components.PerforatedBoardingPassCard
import com.example.ui.theme.AbilletAmber
import com.example.ui.theme.AbilletBorder
import com.example.ui.theme.AbilletNavy
import com.example.ui.theme.AbilletNavyContainer
import com.example.ui.theme.AbilletNavyDark
import com.example.ui.theme.AbilletSlateBg
import com.example.ui.theme.AbilletTextMuted
import com.example.ui.theme.AbilletTextSecondary
import com.example.ui.theme.AirtelRed
import com.example.ui.theme.AirtelRedBg
import com.example.ui.theme.EmeraldContainer
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.MoovBlue
import com.example.ui.theme.MoovBlueBg
import com.example.ui.theme.WhatsAppGreen
import com.example.ui.theme.WhatsAppGreenBg
import com.example.viewmodel.AbilletUiState

@Composable
fun CheckoutScreen(
    uiState: AbilletUiState,
    onBack: () -> Unit,
    onPassengerNameChange: (String) -> Unit,
    onPassengerPhoneChange: (String) -> Unit,
    onSelectPaymentMethod: (PaymentMethod) -> Unit,
    onConfirmPayment: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onBack)

    val isHotel = uiState.selectedHotel != null
    val totalXaf = if (isHotel) {
        (uiState.selectedHotel?.pricePerNightXaf ?: 0) * uiState.hotelNights
    } else {
        uiState.selectedTrip?.priceXaf ?: 15000
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AbilletSlateBg)
    ) {
        Surface(color = AbilletNavy, shadowElevation = 4.dp) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Retour",
                        tint = Color.White
                    )
                }
                Column {
                    Text(
                        text = "Paiement sécurisé",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Text(
                        text = "Zéro frais cachés • Billet instantané SMS & WhatsApp",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.78f)
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Order Summary Card
            item {
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Color.White,
                    border = BorderStroke(1.dp, AbilletBorder)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(AbilletNavyContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (isHotel) Icons.Rounded.Hotel else Icons.Rounded.DirectionsBus,
                                contentDescription = null,
                                tint = AbilletNavy
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            if (isHotel && uiState.selectedHotel != null) {
                                Text(
                                    text = uiState.selectedHotel.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = AbilletNavy
                                )
                                Text(
                                    text = "${uiState.selectedHotel.city} • ${uiState.hotelNights} nuit(s) • ${uiState.selectedDate.displayDate}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = AbilletTextSecondary
                                )
                            } else if (uiState.selectedTrip != null) {
                                val trip = uiState.selectedTrip
                                Text(
                                    text = "${trip.departureCity} → ${trip.arrivalCity}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = AbilletNavy
                                )
                                Text(
                                    text = "${trip.companyName} • ${uiState.selectedDate.displayDate} à ${trip.departureTime} • Siège N°${uiState.selectedSeatNumber ?: 14}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = AbilletTextSecondary
                                )
                            }
                        }
                        Text(
                            text = uiState.currency.format(totalXaf),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                            color = AbilletNavy
                        )
                    }
                }
            }

            // Fast Passenger Form
            item {
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Color.White,
                    border = BorderStroke(1.dp, AbilletBorder)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "1. Coordonnées du passager",
                            style = MaterialTheme.typography.titleMedium,
                            color = AbilletNavy
                        )
                        OutlinedTextField(
                            value = uiState.passengerName,
                            onValueChange = onPassengerNameChange,
                            label = { Text("Nom complet (sur la carte d'identité)") },
                            leadingIcon = { Icon(Icons.Rounded.Person, contentDescription = null) },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = AbilletNavy,
                                unfocusedBorderColor = AbilletBorder
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("passenger_name_input")
                        )
                        OutlinedTextField(
                            value = uiState.passengerPhone,
                            onValueChange = onPassengerPhoneChange,
                            label = { Text("Téléphone Tchad (SMS & WhatsApp)") },
                            leadingIcon = { Icon(Icons.Rounded.Phone, contentDescription = null) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = AbilletNavy,
                                unfocusedBorderColor = AbilletBorder
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("passenger_phone_input")
                        )
                        Text(
                            text = "Votre billet électronique avec QR Code sera envoyé instantanément sur ce numéro.",
                            style = MaterialTheme.typography.bodySmall,
                            color = WhatsAppGreen
                        )
                    }
                }
            }

            // Payment Method Selection
            item {
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Color.White,
                    border = BorderStroke(1.dp, AbilletBorder)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "2. Mode de paiement",
                            style = MaterialTheme.typography.titleMedium,
                            color = AbilletNavy
                        )

                        PaymentMethod.entries.forEach { method ->
                            PaymentMethodRadioCard(
                                method = method,
                                selected = uiState.selectedPaymentMethod == method,
                                onClick = { onSelectPaymentMethod(method) }
                            )
                        }
                    }
                }
            }

            // Price Breakdown & Confirm CTA
            item {
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Color.White,
                    border = BorderStroke(1.dp, AbilletBorder)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Tarif officiel", style = MaterialTheme.typography.bodyMedium, color = AbilletTextSecondary)
                            Text(uiState.currency.format(totalXaf), style = MaterialTheme.typography.titleSmall, color = AbilletNavy)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Frais de service Abillet", style = MaterialTheme.typography.bodyMedium, color = AbilletTextSecondary)
                            Text("0 FCFA (Offert)", style = MaterialTheme.typography.titleSmall, color = EmeraldSuccess)
                        }
                        HorizontalDivider(color = AbilletBorder)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Total à payer", style = MaterialTheme.typography.titleMedium, color = AbilletNavy)
                            Text(
                                text = uiState.currency.format(totalXaf),
                                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold),
                                color = AbilletNavy
                            )
                        }

                        Button(
                            onClick = onConfirmPayment,
                            enabled = !uiState.isProcessingPayment,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("confirm_payment_button"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AbilletAmber,
                                contentColor = AbilletNavyDark
                            )
                        ) {
                            if (uiState.isProcessingPayment) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(22.dp),
                                    color = AbilletNavyDark,
                                    strokeWidth = 2.5.dp
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("Validation en cours...", style = MaterialTheme.typography.labelLarge)
                            } else {
                                Icon(Icons.Rounded.Lock, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Confirmer et payer ${uiState.currency.format(totalXaf)}",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PaymentMethodRadioCard(
    method: PaymentMethod,
    selected: Boolean,
    onClick: () -> Unit
) {
    val (accentColor, bgColor, icon) = when (method) {
        PaymentMethod.AIRTEL_MONEY -> Triple(AirtelRed, AirtelRedBg, Icons.Rounded.PhoneAndroid)
        PaymentMethod.MOOV_MONEY -> Triple(MoovBlue, MoovBlueBg, Icons.Rounded.PhoneAndroid)
        PaymentMethod.CARD -> Triple(AbilletNavy, AbilletNavyContainer, Icons.Rounded.CreditCard)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("payment_method_${method.name}")
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        color = if (selected) bgColor else AbilletSlateBg,
        border = BorderStroke(
            width = if (selected) 1.5.dp else 1.dp,
            color = if (selected) accentColor else AbilletBorder
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            RadioButton(
                selected = selected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(selectedColor = accentColor)
            )
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(accentColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = method.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = AbilletNavy
                )
                Text(
                    text = if (selected) method.ussdHint else method.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (selected) accentColor else AbilletTextSecondary
                )
            }
        }
    }
}

@Composable
fun ETicketPreviewScreen(
    ticket: BookedTicket,
    uiState: AbilletUiState,
    onBackToHome: () -> Unit,
    onViewMyTickets: () -> Unit,
    onShareWhatsAppFeedback: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onBackToHome)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AbilletSlateBg)
    ) {
        Surface(color = AbilletNavy, shadowElevation = 4.dp) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackToHome) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Retour à l'accueil",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Votre Billet Électronique",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = EmeraldContainer,
                    border = BorderStroke(1.dp, EmeraldSuccess.copy(alpha = 0.3f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(EmeraldSuccess),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.CheckCircle,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Réservation confirmée !",
                                style = MaterialTheme.typography.titleMedium,
                                color = EmeraldSuccess
                            )
                            Text(
                                text = "Billet ${ticket.ticketCode} envoyé par SMS et WhatsApp au ${ticket.phoneNumber}.",
                                style = MaterialTheme.typography.bodySmall,
                                color = AbilletNavyDark
                            )
                        }
                    }
                }
            }

            item {
                PerforatedBoardingPassCard(
                    ticket = ticket,
                    currency = uiState.currency
                )
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(
                        onClick = onShareWhatsAppFeedback,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("share_whatsapp_button"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WhatsAppGreen,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(Icons.Rounded.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Partager le billet sur WhatsApp", style = MaterialTheme.typography.labelLarge)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = onViewMyTickets,
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, AbilletNavy)
                        ) {
                            Text("Mes billets", color = AbilletNavy, style = MaterialTheme.typography.labelLarge)
                        }
                        Button(
                            onClick = onBackToHome,
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AbilletNavy,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Nouveau trajet", style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }
        }
    }
}
