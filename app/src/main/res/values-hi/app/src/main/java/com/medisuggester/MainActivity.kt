package com.medisuggester

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.medisuggester.ui.theme.MediSuggesterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediSuggesterTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val nav = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.app_name)) })
        }
    ) { padding ->
        NavHost(
            navController = nav,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") { HomeScreen(nav) }
            composable("symptoms") { SymptomScreen() }
            composable("medicines") { MedicineScreen() }
            composable("labs") { LabAnalyzerScreen() }
            composable("reminders") { ReminderScreen() }
            composable("records") { RecordsScreen() }
            composable("settings") { SettingsScreen() }
        }
    }
}

@Composable
fun HomeScreen(nav: NavHostController) {
    val items = listOf(
        stringResource(R.string.symptom_checker) to "symptoms",
        stringResource(R.string.medicines) to "medicines",
        stringResource(R.string.lab_analyzer) to "labs",
        stringResource(R.string.reminders) to "reminders",
        stringResource(R.string.records) to "records",
        stringResource(R.string.settings) to "settings",
    )

    Column(Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.welcome_title),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.SemiBold
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(items) { (title, route) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { nav.navigate(route) }
                ) {
                    Text(title, modifier = Modifier.padding(18.dp))
                }
            }
        }
        Divider()
        Text(
            text = stringResource(R.string.disclaimer),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable fun SymptomScreen() = SimpleComingSoon(stringResource(R.string.symptom_checker))
@Composable fun MedicineScreen() = SimpleComingSoon(stringResource(R.string.medicines))
@Composable fun LabAnalyzerScreen() = SimpleComingSoon(stringResource(R.string.lab_analyzer))
@Composable fun ReminderScreen() = SimpleComingSoon(stringResource(R.string.reminders))
@Composable fun RecordsScreen() = SimpleComingSoon(stringResource(R.string.records))

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(stringResource(R.string.language), style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { setLocale("en") }) { Text(stringResource(R.string.english)) }
            Button(onClick = { setLocale("hi") }) { Text(stringResource(R.string.hindi)) }
        }
    }
}

private fun setLocale(tag: String) {
    val locales = LocaleListCompat.forLanguageTags(tag)
    AppCompatDelegate.setApplicationLocales(locales)
}

@Composable
private fun SimpleComingSoon(title: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(6.dp))
            Text(text = stringResource(R.string.coming_soon))
        }
    }
}
