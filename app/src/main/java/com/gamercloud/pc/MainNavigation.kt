
package com.gamercloud.pc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Dns
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val GamingBackground = Color(0xFF0A101C)
private val GamingCyan = Color(0xFF00C8FF)

@Composable
fun MainNavigation() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = GamingBackground,
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF111C2B),
                contentColor = GamingCyan
            ) {
                val items = listOf(
                    Triple("Inicio", Icons.Default.Home, 0),
                    Triple("Servidores", Icons.Default.Dns, 1),
                    Triple("Juegos", Icons.Default.SportsEsports, 2),
                    Triple("Ajustes", Icons.Default.Settings, 3)
                )

                items.forEach { item ->
                    NavigationBarItem(
                        selected = selectedTab == item.third,
                        onClick = { selectedTab = item.third },
                        icon = {
                            Icon(item.second, contentDescription = item.first)
                        },
                        label = {
                            Text(item.first, fontSize = 10.sp)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = GamingCyan,
                            selectedTextColor = GamingCyan,
                            indicatorColor = Color(0xFF19364C),
                            unselectedIconColor = Color.LightGray,
                            unselectedTextColor = Color.LightGray
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GamingBackground)
                .padding(padding)
        ) {
            when (selectedTab) {
                0 -> HomeScreen()
                1 -> ServerScreen()
                2 -> GamesScreen()
                3 -> SettingsScreen()
            }
        }
    }
}

@Composable
private fun ScreenTitle(title: String, subtitle: String) {
    Column(
        modifier = Modifier.padding(22.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            "GAMER CLOUD PC",
            color = GamingCyan,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            title,
            color = Color.White,
            fontSize = 27.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(subtitle, color = Color.LightGray, fontSize = 13.sp)
    }
}

@Composable
private fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenTitle("Inicio", "Tu experiencia de juego en la nube")

        Spacer(Modifier.height(45.dp))

        Text("TU PC EN LA NUBE", color = Color.White, fontSize = 21.sp)

        Spacer(Modifier.height(12.dp))

        Text("Servidor pendiente de configurar", color = Color.LightGray)

        Spacer(Modifier.height(35.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(62.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = GamingCyan
            )
        ) {
            Text(
                "JUGAR",
                color = Color.Black,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(18.dp))

        Text(
            "Primero configura tu servidor",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun ServerScreen() {
    Column {
        ScreenTitle("Servidores", "Administra tus conexiones")

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF172438)
            )
        ) {
            Column(Modifier.padding(18.dp)) {
                Text("Mi PC remoto", color = Color.White,
                    fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text("Todavía no hay servidores configurados",
                    color = Color.LightGray)
                Spacer(Modifier.height(14.dp))
                OutlinedButton(onClick = { }) {
                    Text("AGREGAR SERVIDOR")
                }
            }
        }
    }
}

@Composable
private fun GamesScreen() {
    Column {
        ScreenTitle("Mis juegos", "Tu biblioteca de juegos")

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF172438)
            )
        ) {
            Column(Modifier.padding(18.dp)) {
                Text("Biblioteca remota",
                    color = Color.White,
                    fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text("Conecta un PC para consultar tus juegos.",
                    color = Color.LightGray)
            }
        }
    }
}

@Composable
private fun SettingsScreen() {
    Column {
        ScreenTitle("Configuración", "Personaliza tu experiencia")

        val options = listOf(
            "Calidad de transmisión",
            "Resolución y FPS",
            "Controles táctiles",
            "Mando Bluetooth",
            "Audio",
            "Seguridad",
            "Información de la aplicación"
        )

        options.forEach { option ->
            ListItem(
                headlineContent = {
                    Text(option, color = Color.White)
                },
                trailingContent = {
                    Text("›", color = GamingCyan, fontSize = 23.sp)
                },
                colors = ListItemDefaults.colors(
                    containerColor = GamingBackground
                )
            )
        }
    }
}
