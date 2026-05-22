package com.unibo.android.ui.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.unibo.android.ui.common.Header
import com.unibo.android.ui.theme.Header
import com.unibo.android.uicompose.navigation.Routes

@Composable
fun CreateNoteScreen(navController: NavController) {
    var noteTitle by remember {
        mutableStateOf("")
    }

    var noteText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Header("New Note")

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(15.dp)
                .background(Header)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                OutlinedTextField(
                    value = noteTitle,
                    onValueChange = {
                        noteTitle = it
                    },
                    label = {
                        Text("Insert title")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = noteText,
                    onValueChange = {
                        noteText = it
                    },
                    label = {
                        Text("Insert text")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )

                Button(
                    onClick = {
                        if(noteText.isNotBlank() && noteTitle.isNotBlank()) {
                            //TO DO API TO CHECK IF IS OKAY


                            // IF IS OKAY THEN GO TO VAULT
                            navController.navigate(Routes.VAULT)
                        }

                    },
                    modifier = Modifier
                        .align(Alignment.End)
                ) {
                    Text("Create note")
                }
            }
        }
    }
}
