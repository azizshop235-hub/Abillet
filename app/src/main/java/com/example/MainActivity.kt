package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.ConfirmationNumber
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ui.components.AbilletTopHeader
import com.example.ui.screens.AgencyAccountBottomSheet
import com.example.ui.screens.CheckoutScreen
import com.example.ui.screens.CityPickerBottomSheet
import com.example.ui.screens.ETicketPreviewScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MyTicketsScreen
import com.example.ui.screens.SearchResultsScreen
import com.example.ui.screens.SupportBottomSheet
import com.example.ui.theme.AbilletAmber
import com.example.ui.theme.AbilletNavy
import com.example.ui.theme.AbilletNavyContainer
import com.example.ui.theme.AbilletNavyDark
import com.example.ui.theme.AbilletSlateBg
import com.example.ui.theme.AbilletTextSecondary
import com.example.ui.theme.AbilletTheme
import com.example.viewmodel.AbilletViewModel
import kotlinx.coroutines.launch

const val ROUTE_HOME = "home"
const val ROUTE_RESULTS = "results"
const val ROUTE_CHECKOUT = "checkout"
const val ROUTE_ETICKET = "eticket"
const val ROUTE_MY_TICKETS = "my_tickets"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AbilletTheme(darkTheme = false) {
                AbilletAppRoot()
            }
        }
    }
}

@Composable
fun AbilletAppRoot(
    viewModel: AbilletViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ROUTE_HOME
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val showBottomOrRailNav = currentRoute in setOf(ROUTE_HOME, ROUTE_RESULTS, ROUTE_MY_TICKETS)

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isExpandedWidth = maxWidth >= 700.dp

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = AbilletSlateBg,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = {
                Column(
                    modifier = Modifier
                        .background(AbilletNavyDark)
                        .statusBarsPadding()
                ) {
                    AbilletTopHeader(
                        currency = uiState.currency,
                        agencyLoggedInName = uiState.agencyLoggedInName,
                        onCurrencySelected = { viewModel.setCurrency(it) },
                        onSupportClick = { viewModel.setShowSupportSheet(true) },
                        onAgencyClick = { viewModel.setShowAgencySheet(true) }
                    )
                }
            },
            bottomBar = {
                if (showBottomOrRailNav && !isExpandedWidth) {
                    AbilletBottomNavigationBar(
                        currentRoute = currentRoute,
                        ticketsCount = uiState.bookedTickets.size,
                        onNavigate = { route ->
                            navController.navigate(route) {
                                popUpTo(ROUTE_HOME) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (showBottomOrRailNav && isExpandedWidth) {
                    AbilletSideNavigationRail(
                        currentRoute = currentRoute,
                        ticketsCount = uiState.bookedTickets.size,
                        onNavigate = { route ->
                            navController.navigate(route) {
                                popUpTo(ROUTE_HOME) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }

                Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    NavHost(
                        navController = navController,
                        startDestination = ROUTE_HOME
                    ) {
                        composable(ROUTE_HOME) {
                            HomeScreen(
                                uiState = uiState,
                                onTabSelected = { viewModel.setSearchTab(it) },
                                onOpenCityPicker = { viewModel.openCityPicker(it) },
                                onSwapCities = { viewModel.swapBusCities() },
                                onSelectDate = { viewModel.selectQuickDate(it) },
                                onAdjustNights = { viewModel.adjustHotelNights(it) },
                                onAdjustGuests = { viewModel.adjustHotelGuests(it) },
                                onSearchBus = {
                                    viewModel.searchBuses()
                                    navController.navigate(ROUTE_RESULTS)
                                },
                                onSearchHotel = {
                                    viewModel.searchHotels()
                                    navController.navigate(ROUTE_RESULTS)
                                },
                                onSelectPopularRoute = { route ->
                                    viewModel.searchPopularRoute(route)
                                    navController.navigate(ROUTE_RESULTS)
                                },
                                onReserveHotel = { hotel ->
                                    viewModel.selectHotelForBooking(hotel)
                                    navController.navigate(ROUTE_CHECKOUT)
                                },
                                onOpenSupport = { viewModel.setShowSupportSheet(true) },
                                onOpenAgency = { viewModel.setShowAgencySheet(true) }
                            )
                        }

                        composable(ROUTE_RESULTS) {
                            SearchResultsScreen(
                                uiState = uiState,
                                onBack = { navController.popBackStack() },
                                onSwapAndRefresh = {
                                    viewModel.swapBusCities()
                                    viewModel.searchBuses()
                                },
                                onSelectBusFilter = { viewModel.setBusFilter(it) },
                                onToggleTripSeatMap = { viewModel.toggleTripSeatMap(it) },
                                onSelectSeat = { trip, seat -> viewModel.selectSeat(trip, seat) },
                                onContinueToPayment = {
                                    navController.navigate(ROUTE_CHECKOUT)
                                },
                                onSelectHotel = { hotel ->
                                    viewModel.selectHotelForBooking(hotel)
                                    navController.navigate(ROUTE_CHECKOUT)
                                }
                            )
                        }

                        composable(ROUTE_CHECKOUT) {
                            CheckoutScreen(
                                uiState = uiState,
                                onBack = { navController.popBackStack() },
                                onPassengerNameChange = { viewModel.updatePassengerName(it) },
                                onPassengerPhoneChange = { viewModel.updatePassengerPhone(it) },
                                onSelectPaymentMethod = { viewModel.selectPaymentMethod(it) },
                                onConfirmPayment = {
                                    viewModel.confirmPaymentAndGenerateTicket {
                                        navController.navigate(ROUTE_ETICKET)
                                    }
                                }
                            )
                        }

                        composable(ROUTE_ETICKET) {
                            val ticket = uiState.activeTicketPreview ?: uiState.bookedTickets.first()
                            ETicketPreviewScreen(
                                ticket = ticket,
                                uiState = uiState,
                                onBackToHome = {
                                    viewModel.clearActiveTicketPreview()
                                    navController.popBackStack(ROUTE_HOME, inclusive = false)
                                },
                                onViewMyTickets = {
                                    navController.navigate(ROUTE_MY_TICKETS) {
                                        popUpTo(ROUTE_HOME) { inclusive = false }
                                    }
                                },
                                onShareWhatsAppFeedback = {
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar(
                                            "Billet ${ticket.ticketCode} prêt à être partagé via WhatsApp !"
                                        )
                                    }
                                }
                            )
                        }

                        composable(ROUTE_MY_TICKETS) {
                            MyTicketsScreen(
                                tickets = uiState.bookedTickets,
                                currency = uiState.currency,
                                onTicketClick = { ticket ->
                                    viewModel.openTicketPreview(ticket)
                                    navController.navigate(ROUTE_ETICKET)
                                },
                                onBookNewClick = {
                                    navController.navigate(ROUTE_HOME) {
                                        popUpTo(ROUTE_HOME) { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

        // City Picker Bottom Sheet
        val cityTarget = uiState.cityPickerTarget
        if (cityTarget != null) {
            CityPickerBottomSheet(
                target = cityTarget,
                selectedDeparture = uiState.departureCity,
                selectedArrival = uiState.arrivalCity,
                onCitySelected = { viewModel.selectCity(it) },
                onDismiss = { viewModel.dismissCityPicker() }
            )
        }

        // Support / WhatsApp Bottom Sheet
        if (uiState.showSupportSheet) {
            SupportBottomSheet(
                onDismiss = { viewModel.setShowSupportSheet(false) }
            )
        }

        // Espace Agence / Account Bottom Sheet
        if (uiState.showAgencySheet) {
            AgencyAccountBottomSheet(
                loggedInLabel = uiState.agencyLoggedInName,
                onSelectAccount = { label ->
                    viewModel.signInAgencyOrTraveler(label)
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Connecté en tant que $label")
                    }
                },
                onDismiss = { viewModel.setShowAgencySheet(false) }
            )
        }
    }
}

@Composable
private fun AbilletBottomNavigationBar(
    currentRoute: String,
    ticketsCount: Int,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 6.dp
    ) {
        NavigationBarItem(
            selected = currentRoute == ROUTE_HOME,
            onClick = { onNavigate(ROUTE_HOME) },
            icon = {
                Icon(
                    imageVector = if (currentRoute == ROUTE_HOME) Icons.Rounded.Search else Icons.Outlined.Search,
                    contentDescription = "Accueil"
                )
            },
            label = { Text("Accueil", style = MaterialTheme.typography.labelSmall) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = AbilletNavy,
                selectedTextColor = AbilletNavy,
                indicatorColor = AbilletNavyContainer,
                unselectedIconColor = AbilletTextSecondary,
                unselectedTextColor = AbilletTextSecondary
            ),
            modifier = Modifier.testTag("nav_home")
        )
        NavigationBarItem(
            selected = currentRoute == ROUTE_RESULTS,
            onClick = { onNavigate(ROUTE_RESULTS) },
            icon = {
                Icon(
                    imageVector = if (currentRoute == ROUTE_RESULTS) Icons.Rounded.DirectionsBus else Icons.Outlined.DirectionsBus,
                    contentDescription = "Départs & Hôtels"
                )
            },
            label = { Text("Départs", style = MaterialTheme.typography.labelSmall) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = AbilletNavy,
                selectedTextColor = AbilletNavy,
                indicatorColor = AbilletNavyContainer,
                unselectedIconColor = AbilletTextSecondary,
                unselectedTextColor = AbilletTextSecondary
            ),
            modifier = Modifier.testTag("nav_results")
        )
        NavigationBarItem(
            selected = currentRoute == ROUTE_MY_TICKETS,
            onClick = { onNavigate(ROUTE_MY_TICKETS) },
            icon = {
                BadgedBox(
                    badge = {
                        if (ticketsCount > 0) {
                            Badge(
                                containerColor = AbilletAmber,
                                contentColor = AbilletNavyDark
                            ) {
                                Text(ticketsCount.toString())
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (currentRoute == ROUTE_MY_TICKETS) {
                            Icons.Rounded.ConfirmationNumber
                        } else {
                            Icons.Outlined.ConfirmationNumber
                        },
                        contentDescription = "Mes billets"
                    )
                }
            },
            label = { Text("Mes billets", style = MaterialTheme.typography.labelSmall) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = AbilletNavy,
                selectedTextColor = AbilletNavy,
                indicatorColor = AbilletNavyContainer,
                unselectedIconColor = AbilletTextSecondary,
                unselectedTextColor = AbilletTextSecondary
            ),
            modifier = Modifier.testTag("nav_my_tickets")
        )
    }
}

@Composable
private fun AbilletSideNavigationRail(
    currentRoute: String,
    ticketsCount: Int,
    onNavigate: (String) -> Unit
) {
    NavigationRail(
        containerColor = Color.White
    ) {
        NavigationRailItem(
            selected = currentRoute == ROUTE_HOME,
            onClick = { onNavigate(ROUTE_HOME) },
            icon = { Icon(Icons.Rounded.Search, contentDescription = "Accueil") },
            label = { Text("Accueil") },
            colors = NavigationRailItemDefaults.colors(
                selectedIconColor = AbilletNavy,
                indicatorColor = AbilletNavyContainer
            )
        )
        NavigationRailItem(
            selected = currentRoute == ROUTE_RESULTS,
            onClick = { onNavigate(ROUTE_RESULTS) },
            icon = { Icon(Icons.Rounded.DirectionsBus, contentDescription = "Départs") },
            label = { Text("Départs") },
            colors = NavigationRailItemDefaults.colors(
                selectedIconColor = AbilletNavy,
                indicatorColor = AbilletNavyContainer
            )
        )
        NavigationRailItem(
            selected = currentRoute == ROUTE_MY_TICKETS,
            onClick = { onNavigate(ROUTE_MY_TICKETS) },
            icon = {
                BadgedBox(
                    badge = {
                        if (ticketsCount > 0) {
                            Badge(containerColor = AbilletAmber, contentColor = AbilletNavyDark) {
                                Text(ticketsCount.toString())
                            }
                        }
                    }
                ) {
                    Icon(Icons.Rounded.ConfirmationNumber, contentDescription = "Mes billets")
                }
            },
            label = { Text("Mes billets") },
            colors = NavigationRailItemDefaults.colors(
                selectedIconColor = AbilletNavy,
                indicatorColor = AbilletNavyContainer
            )
        )
    }
}
