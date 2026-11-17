package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.rounded.AirlineSeatReclineNormal
import androidx.compose.material.icons.rounded.Business
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.ConfirmationNumber
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.icons.rounded.Hotel
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.BookedTicket
import com.example.model.BusTrip
import com.example.model.Currency
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
import com.example.ui.theme.AirtelRed
import com.example.ui.theme.EmeraldContainer
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.MoovBlue
import com.example.ui.theme.SeatOccupied
import com.example.ui.theme.SeatOccupiedText
import com.example.ui.theme.SeatSelected
import com.example.ui.theme.WhatsAppGreen
import com.example.ui.theme.WhatsAppGreenBg

@Composable
fun AbilletBrandLogo(
    modifier: Modifier = Modifier,
    lightText: Boolean = true
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Stylized A + Ticket Notch + Road Ribbon icon
        Canvas(modifier = Modifier.size(34.dp)) {
            val w = size.width
            val h = size.height
            drawRoundRect(
                color = if (lightText) AbilletAmber else AbilletNavy,
                topLeft = Offset.Zero,
                size = size,
                cornerRadius = CornerRadius(w * 0.28f, w * 0.28f)
            )
            // Ticket side notches
            val notchColor = if (lightText) AbilletNavyDark else Color.White
            drawCircle(
                color = notchColor,
                radius = w * 0.11f,
                center = Offset(0f, h * 0.52f)
            )
            drawCircle(
                color = notchColor,
                radius = w * 0.11f,
                center = Offset(w, h * 0.52f)
            )
            // Stylized A strokes
            val strokeColor = if (lightText) AbilletNavyDark else Color.White
            val aPath = Path().apply {
                moveTo(w * 0.27f, h * 0.78f)
                lineTo(w * 0.50f, h * 0.20f)
                lineTo(w * 0.73f, h * 0.78f)
            }
            drawPath(
                path = aPath,
                color = strokeColor,
                style = Stroke(width = w * 0.13f, cap = StrokeCap.Round)
            )
            // Road crossbar inside the A
            drawLine(
                color = if (lightText) Color.White else AbilletAmber,
                start = Offset(w * 0.34f, h * 0.58f),
                end = Offset(w * 0.66f, h * 0.58f),
                strokeWidth = w * 0.11f,
                cap = StrokeCap.Round
            )
        }
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Abillet",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    ),
                    color = if (lightText) Color.White else AbilletNavy
                )
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(AbilletAmber)
                )
            }
            Text(
                text = "TCHAD MOBILITÉ",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 9.sp,
                    letterSpacing = 1.1.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = if (lightText) Color.White.copy(alpha = 0.78f) else AbilletTextSecondary
            )
        }
    }
}

@Composable
fun AbilletTopHeader(
    currency: Currency,
    agencyLoggedInName: String?,
    onCurrencySelected: (Currency) -> Unit,
    onSupportClick: () -> Unit,
    onAgencyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currencyExpanded by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = AbilletNavyDark,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AbilletBrandLogo(lightText = true)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Currency Selector
                Box {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color.White.copy(alpha = 0.12f),
                        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.2f)),
                        modifier = Modifier
                            .testTag("currency_selector_button")
                            .clip(RoundedCornerShape(20.dp))
                            .clickable { currencyExpanded = true }
                            .minimumInteractiveComponentSize()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text = currency.code,
                                style = MaterialTheme.typography.labelMedium,
                                color = AbilletAmber
                            )
                            Icon(
                                imageVector = Icons.Rounded.ExpandMore,
                                contentDescription = "Changer de devise",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = currencyExpanded,
                        onDismissRequest = { currencyExpanded = false }
                    ) {
                        Currency.entries.forEach { option ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "${option.code} - ${option.label}",
                                        fontWeight = if (option == currency) FontWeight.Bold else FontWeight.Normal
                                    )
                                },
                                onClick = {
                                    onCurrencySelected(option)
                                    currencyExpanded = false
                                }
                            )
                        }
                    }
                }

                // WhatsApp / Aide button
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = WhatsAppGreen.copy(alpha = 0.22f),
                    border = BorderStroke(1.dp, WhatsAppGreen.copy(alpha = 0.55f)),
                    modifier = Modifier
                        .testTag("header_support_button")
                        .clip(RoundedCornerShape(20.dp))
                        .clickable(onClick = onSupportClick)
                        .minimumInteractiveComponentSize()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 9.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.SupportAgent,
                            contentDescription = "Aide et WhatsApp",
                            tint = Color(0xFF86EFAC),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Aide",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White
                        )
                    }
                }

                // Se connecter / Espace Agence
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = if (agencyLoggedInName != null) AbilletAmber else Color.White.copy(alpha = 0.14f),
                    modifier = Modifier
                        .testTag("header_agency_button")
                        .clip(RoundedCornerShape(20.dp))
                        .clickable(onClick = onAgencyClick)
                        .minimumInteractiveComponentSize()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = if (agencyLoggedInName != null) Icons.Rounded.Business else Icons.Rounded.Person,
                            contentDescription = "Se connecter ou Espace Agence",
                            tint = if (agencyLoggedInName != null) AbilletNavyDark else Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = if (agencyLoggedInName != null) "Agence" else "Compte",
                            style = MaterialTheme.typography.labelMedium,
                            color = if (agencyLoggedInName != null) AbilletNavyDark else Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PaymentLogosStrip(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PaymentPillBadge("Airtel Money", AirtelRed, Color.White)
        PaymentPillBadge("Moov Money", MoovBlue, Color.White)
        PaymentPillBadge("VISA", Color(0xFF1A1F71), Color.White)
        PaymentPillBadge("MasterCard", Color(0xFFEA580C), Color.White)
    }
}

@Composable
fun PaymentPillBadge(
    label: String,
    containerColor: Color,
    contentColor: Color
) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = containerColor
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
            ),
            color = contentColor,
            modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
        )
    }
}

@Composable
fun InteractiveBusSeatMap(
    trip: BusTrip,
    selectedSeat: Int?,
    currency: Currency,
    onSeatClick: (Int) -> Unit,
    onContinueToPayment: () -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val isWide = maxWidth >= 560.dp
        if (isWide) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.Top
            ) {
                BusDeckDiagram(
                    trip = trip,
                    selectedSeat = selectedSeat,
                    onSeatClick = onSeatClick,
                    modifier = Modifier.weight(1.25f)
                )
                SeatSelectionSummaryCard(
                    trip = trip,
                    selectedSeat = selectedSeat,
                    currency = currency,
                    onContinueToPayment = onContinueToPayment,
                    modifier = Modifier.weight(1f)
                )
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                BusDeckDiagram(
                    trip = trip,
                    selectedSeat = selectedSeat,
                    onSeatClick = onSeatClick,
                    modifier = Modifier.fillMaxWidth()
                )
                SeatSelectionSummaryCard(
                    trip = trip,
                    selectedSeat = selectedSeat,
                    currency = currency,
                    onContinueToPayment = onContinueToPayment,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun BusDeckDiagram(
    trip: BusTrip,
    selectedSeat: Int?,
    onSeatClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = AbilletSurfaceVariant,
        border = BorderStroke(1.dp, AbilletBorder)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Color Legend
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SeatLegendItem("Disponible", Color.White, AbilletNavy)
                SeatLegendItem("Occupé", SeatOccupied, SeatOccupied)
                SeatLegendItem("Sélectionné", SeatSelected, SeatSelected)
            }

            HorizontalDivider(color = AbilletBorder)

            // Front of the bus: Steering wheel + Door
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .border(1.dp, AbilletBorder, RoundedCornerShape(10.dp))
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Steering wheel vector
                    Canvas(modifier = Modifier.size(24.dp)) {
                        val r = size.minDimension / 2f
                        drawCircle(
                            color = AbilletNavy,
                            radius = r - 2f,
                            style = Stroke(width = 3.5f)
                        )
                        drawCircle(color = AbilletNavy, radius = 3.5f)
                        drawLine(
                            color = AbilletNavy,
                            start = Offset(2f, center.y),
                            end = Offset(size.width - 2f, center.y),
                            strokeWidth = 3f
                        )
                        drawLine(
                            color = AbilletNavy,
                            start = Offset(center.x, center.y),
                            end = Offset(center.x, size.height - 2f),
                            strokeWidth = 3f
                        )
                    }
                    Text(
                        text = "Chauffeur",
                        style = MaterialTheme.typography.labelMedium,
                        color = AbilletNavy
                    )
                }
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = AbilletAmberContainer
                ) {
                    Text(
                        text = "Porte avant ↗",
                        style = MaterialTheme.typography.labelSmall,
                        color = AbilletNavyDark,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            // 12 rows of 4 seats (1..48) + Rear 2 seats (49, 50) = 50 seats
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .border(1.dp, AbilletBorder, RoundedCornerShape(12.dp))
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                for (rowIdx in 0 until 12) {
                    val base = rowIdx * 4 + 1
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            SeatBox(
                                seatNumber = base,
                                isOccupied = base in trip.occupiedSeats,
                                isSelected = selectedSeat == base,
                                onClick = { onSeatClick(base) }
                            )
                            SeatBox(
                                seatNumber = base + 1,
                                isOccupied = (base + 1) in trip.occupiedSeats,
                                isSelected = selectedSeat == base + 1,
                                onClick = { onSeatClick(base + 1) }
                            )
                        }

                        Text(
                            text = "${rowIdx + 1}",
                            style = MaterialTheme.typography.labelSmall,
                            color = AbilletTextMuted,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(20.dp)
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            SeatBox(
                                seatNumber = base + 2,
                                isOccupied = (base + 2) in trip.occupiedSeats,
                                isSelected = selectedSeat == base + 2,
                                onClick = { onSeatClick(base + 2) }
                            )
                            SeatBox(
                                seatNumber = base + 3,
                                isOccupied = (base + 3) in trip.occupiedSeats,
                                isSelected = selectedSeat == base + 3,
                                onClick = { onSeatClick(base + 3) }
                            )
                        }
                    }
                }

                // Back row: seats 49 & 50
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SeatBox(
                        seatNumber = 49,
                        isOccupied = 49 in trip.occupiedSeats,
                        isSelected = selectedSeat == 49,
                        onClick = { onSeatClick(49) }
                    )
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = AbilletSurfaceVariant
                    ) {
                        Text(
                            text = "ARRIÈRE • 50 PLACES",
                            style = MaterialTheme.typography.labelSmall,
                            color = AbilletTextSecondary,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                    SeatBox(
                        seatNumber = 50,
                        isOccupied = 50 in trip.occupiedSeats,
                        isSelected = selectedSeat == 50,
                        onClick = { onSeatClick(50) }
                    )
                }
            }
        }
    }
}

@Composable
private fun SeatLegendItem(
    label: String,
    fillColor: Color,
    borderColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(fillColor)
                .border(1.5.dp, borderColor, RoundedCornerShape(4.dp))
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = AbilletTextSecondary
        )
    }
}

@Composable
private fun SeatBox(
    seatNumber: Int,
    isOccupied: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val bgColor by animateColorAsState(
        targetValue = when {
            isOccupied -> SeatOccupied
            isSelected -> SeatSelected
            else -> Color.White
        },
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "seatBg"
    )
    val borderColor = when {
        isOccupied -> SeatOccupied
        isSelected -> SeatSelected
        else -> AbilletNavy
    }
    val textColor = when {
        isOccupied -> SeatOccupiedText
        isSelected -> Color.White
        else -> AbilletNavy
    }

    Box(
        modifier = Modifier
            .testTag("seat_$seatNumber")
            .size(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .border(1.5.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable(enabled = !isOccupied, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(3.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(textColor.copy(alpha = 0.45f))
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = seatNumber.toString(),
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                ),
                color = textColor
            )
        }
    }
}

@Composable
fun SeatSelectionSummaryCard(
    trip: BusTrip,
    selectedSeat: Int?,
    currency: Currency,
    onContinueToPayment: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = AbilletNavyContainer.copy(alpha = 0.6f),
        border = BorderStroke(1.dp, AbilletNavy.copy(alpha = 0.18f))
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Récapitulatif de votre place",
                style = MaterialTheme.typography.titleMedium,
                color = AbilletNavy
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AirlineSeatReclineNormal,
                        contentDescription = null,
                        tint = AbilletNavy,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = if (selectedSeat != null) "Siège N°$selectedSeat" else "Aucun siège choisi",
                        style = MaterialTheme.typography.titleSmall,
                        color = if (selectedSeat != null) EmeraldSuccess else AbilletTextSecondary
                    )
                }
                Text(
                    text = currency.format(trip.priceXaf),
                    style = MaterialTheme.typography.titleLarge,
                    color = AbilletNavy
                )
            }
            Text(
                text = "${trip.companyName} • Départ ${trip.departureTime} (${trip.departureStation})",
                style = MaterialTheme.typography.bodySmall,
                color = AbilletTextSecondary
            )
            Button(
                onClick = onContinueToPayment,
                enabled = selectedSeat != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("continue_to_payment_button"),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AbilletAmber,
                    contentColor = AbilletNavyDark,
                    disabledContainerColor = SeatOccupied,
                    disabledContentColor = SeatOccupiedText
                )
            ) {
                Text(
                    text = if (selectedSeat != null) {
                        "Continuer vers le paiement"
                    } else {
                        "Touchez un siège libre (1 à 50)"
                    },
                    style = MaterialTheme.typography.labelLarge
                )
                if (selectedSeat != null) {
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun QrCodeMatrixCanvas(
    code: String,
    modifier: Modifier = Modifier,
    size: Dp = 132.dp
) {
    val matrix = remember(code) { buildQrMatrix(code) }

    Surface(
        modifier = modifier.size(size),
        shape = RoundedCornerShape(10.dp),
        color = Color.White,
        border = BorderStroke(1.dp, AbilletBorder)
    ) {
        Canvas(
            modifier = Modifier
                .size(size)
                .padding(10.dp)
        ) {
            val grid = matrix.size
            val cellSize = this.size.width / grid
            for (r in 0 until grid) {
                for (c in 0 until grid) {
                    if (matrix[r][c]) {
                        drawRect(
                            color = AbilletNavyDark,
                            topLeft = Offset(c * cellSize, r * cellSize),
                            size = Size(cellSize + 0.4f, cellSize + 0.4f)
                        )
                    }
                }
            }
        }
    }
}

private fun buildQrMatrix(seedText: String): Array<BooleanArray> {
    val n = 21
    val grid = Array(n) { BooleanArray(n) }

    fun drawFinder(top: Int, left: Int) {
        for (r in 0 until 7) {
            for (c in 0 until 7) {
                val isBorder = r == 0 || r == 6 || c == 0 || c == 6
                val isCenter = r in 2..4 && c in 2..4
                grid[top + r][left + c] = isBorder || isCenter
            }
        }
    }

    drawFinder(0, 0)
    drawFinder(0, n - 7)
    drawFinder(n - 7, 0)

    var hash = seedText.hashCode()
    for (r in 0 until n) {
        for (c in 0 until n) {
            val inTopLeft = r < 8 && c < 8
            val inTopRight = r < 8 && c >= n - 8
            val inBottomLeft = r >= n - 8 && c < 8
            if (!inTopLeft && !inTopRight && !inBottomLeft) {
                hash = hash * 1664525 + 1013904223 + (r * 31 + c)
                grid[r][c] = (hash and 1) == 1 || (r == 6 && c % 2 == 0) || (c == 6 && r % 2 == 0)
            }
        }
    }
    return grid
}

@Composable
fun PerforatedBoardingPassCard(
    ticket: BookedTicket,
    currency: Currency,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .testTag("boarding_pass_card"),
        shape = RoundedCornerShape(18.dp),
        color = Color.White,
        shadowElevation = 6.dp,
        border = BorderStroke(1.dp, AbilletBorder)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Top Navy Ticket Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AbilletNavy)
                    .padding(horizontal = 18.dp, vertical = 14.dp)
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
                        Icon(
                            imageVector = if (ticket.isHotelBooking) Icons.Rounded.Hotel else Icons.Rounded.DirectionsBus,
                            contentDescription = null,
                            tint = AbilletAmber,
                            modifier = Modifier.size(24.dp)
                        )
                        Column {
                            Text(
                                text = if (ticket.isHotelBooking) "BON DE RÉSERVATION HÔTEL" else "BILLET ÉLECTRONIQUE ABILLET",
                                style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 0.8.sp),
                                color = AbilletAmber
                            )
                            Text(
                                text = ticket.companyName ?: ticket.hotelName ?: "Abillet Tchad",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color.White.copy(alpha = 0.14f)
                    ) {
                        Text(
                            text = ticket.ticketCode,
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }
            }

            // Main Trip / Hotel Info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (!ticket.isHotelBooking) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "DÉPART",
                                style = MaterialTheme.typography.labelSmall,
                                color = AbilletTextMuted
                            )
                            Text(
                                text = ticket.departureCity.orEmpty(),
                                style = MaterialTheme.typography.headlineSmall,
                                color = AbilletNavy
                            )
                            Text(
                                text = ticket.departureTime.orEmpty(),
                                style = MaterialTheme.typography.titleMedium,
                                color = AbilletAmber
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                                contentDescription = null,
                                tint = AbilletNavy
                            )
                            Text(
                                text = ticket.duration.orEmpty(),
                                style = MaterialTheme.typography.labelSmall,
                                color = AbilletTextSecondary
                            )
                        }

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "ARRIVÉE",
                                style = MaterialTheme.typography.labelSmall,
                                color = AbilletTextMuted
                            )
                            Text(
                                text = ticket.arrivalCity.orEmpty(),
                                style = MaterialTheme.typography.headlineSmall,
                                color = AbilletNavy,
                                textAlign = TextAlign.End
                            )
                            Text(
                                text = ticket.busType.orEmpty(),
                                style = MaterialTheme.typography.labelSmall,
                                color = AbilletTextSecondary,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "HÉBERGEMENT CONFIRMÉ",
                                style = MaterialTheme.typography.labelSmall,
                                color = AbilletTextMuted
                            )
                            Text(
                                text = ticket.hotelName.orEmpty(),
                                style = MaterialTheme.typography.titleLarge,
                                color = AbilletNavy
                            )
                            Text(
                                text = "${ticket.hotelCity} • ${ticket.hotelAddress}",
                                style = MaterialTheme.typography.bodySmall,
                                color = AbilletTextSecondary
                            )
                        }
                    }
                }

                // Passenger & Seat grid
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = AbilletSlateBg,
                    border = BorderStroke(1.dp, AbilletBorder)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TicketField("PASSAGER", ticket.passengerName, Modifier.weight(1.2f))
                            TicketField(
                                if (ticket.isHotelBooking) "DURÉE" else "SIÈGE",
                                if (ticket.isHotelBooking) "${ticket.hotelNights ?: 1} nuit(s)" else "N°${ticket.seatNumber ?: 14}",
                                Modifier.weight(0.8f),
                                highlight = true
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TicketField("DATE", ticket.travelDate, Modifier.weight(1.2f))
                            TicketField("TÉLÉPHONE", ticket.phoneNumber, Modifier.weight(0.8f))
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Place,
                                contentDescription = null,
                                tint = AbilletNavy,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = if (ticket.isHotelBooking) {
                                    "Accueil : ${ticket.hotelAddress}"
                                } else {
                                    "Embarquement : ${ticket.boardingStation}"
                                },
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                                color = AbilletNavy
                            )
                        }
                    }
                }
            }

            // Perforated Tear Line with left & right semi-circle cutouts
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxWidth().height(24.dp)) {
                    val y = size.height / 2f
                    drawLine(
                        color = AbilletBorder,
                        start = Offset(18f, y),
                        end = Offset(size.width - 18f, y),
                        strokeWidth = 3f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 10f), 0f)
                    )
                    drawCircle(
                        color = AbilletSlateBg,
                        radius = 12.dp.toPx(),
                        center = Offset(0f, y)
                    )
                    drawCircle(
                        color = AbilletSlateBg,
                        radius = 12.dp.toPx(),
                        center = Offset(size.width, y)
                    )
                }
            }

            // QR Code + Validation Instructions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                QrCodeMatrixCanvas(code = ticket.ticketCode, size = 112.dp)

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = EmeraldContainer
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Verified,
                                contentDescription = null,
                                tint = EmeraldSuccess,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "Payé • ${currency.format(ticket.totalPriceXaf)}",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = EmeraldSuccess
                            )
                        }
                    }

                    Text(
                        text = ticket.ticketCode,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.sp
                        ),
                        color = AbilletNavy
                    )

                    Text(
                        text = if (ticket.isHotelBooking) {
                            "Présentez ce QR Code ou ce SMS à la réception de l'hôtel."
                        } else {
                            "Présentez ce QR Code ou ce SMS au chef de gare."
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = AbilletTextSecondary
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.PhoneAndroid,
                            contentDescription = null,
                            tint = WhatsAppGreen,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "Envoyé via SMS & WhatsApp",
                            style = MaterialTheme.typography.labelSmall,
                            color = WhatsAppGreen
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TicketField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    highlight: Boolean = false
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = AbilletTextMuted
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = if (highlight) FontWeight.ExtraBold else FontWeight.SemiBold
            ),
            color = if (highlight) EmeraldSuccess else AbilletNavy,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
