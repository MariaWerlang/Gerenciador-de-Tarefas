package com.example.test

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.fzlbpms.Test.model.Tarefa.Tarefa
import com.example.test.ui.theme.TestTheme
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenContent(DrawerState(initialValue = DrawerValue.Closed))
        }
    }
}

@Composable
fun MainScreenContent(drawerState: DrawerState) {
    val scaffoldState = rememberScaffoldState(drawerState = drawerState)
    var scope = rememberCoroutineScope()
    Scaffold (
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {Text(text = "TaskWerlangApp")},
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Drawer Menu"
                        )
                    }

                }
            )
        },
        drawerBackgroundColor = Color.Red,
        drawerGesturesEnabled = drawerState.isOpen,
        drawerContent = {
            Box(
                modifier = Modifier
                    .background(Color.Magenta)
                    .height(16.dp)
            ){
                Text(text = "Opções!!!")
            }
            Column() {
                Text(text = "Opção Menu")
                Text(text = "Opção Menu")
                Text(text = "Opção Menu")
                
            }
        },
        content = {
                paddingValues -> Log.i ("paddingValues", "$paddingValues")
            Column (
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxSize()
            ) {
                MySeachFiled(modificador = Modifier.fillMaxWidth())

                //val calendar = Calendar.getInstance()

                //listOf<Tarefa>(Tarefa("Estudar para a prova de calculo", "Capitulo do livro xyz", Date(), calendar.set(2023 6 17)))

                /*val tProvaDeCalculo = Tarefa(
                    "Estudar para a Prova de Calculo",
                    "Capitulo do livro xyz",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val tProvaDekotlin = Tarefa(
                    "Estudar para a Prova de kotlin",
                    "Capitulo do livro xyz",
                    Date(),
                    Date(),
                    status = 0.0
                )*/

                //var minhaListaDeTarefas = listOf<Tarefa>(tProvaDeCalculo, tProvaDekotlin)


                //MyTaskWidgetList(minhaListaDeTarefas)

                /*MyTaskWidget(
                    modificador = Modifier.fillMaxWidth(),
                    taskName = "Preparar Aula LazyList/LazyColum",
                    taskDetails = "É bem melhor usar lazylist ao inves de colum",
                    deadEndDate = Date()
                )
                MyTaskWidget(
                    modificador = Modifier.fillMaxWidth(),
                    taskName = "Prova de matemática",
                    taskDetails = "Estudar Culculo caoitulo 1 e 2",
                    deadEndDate = Date()
                )*/
            }
        },
        bottomBar = {
            BottomAppBar(
                content = { Text("asdf")}
            )
        },

        isFloatingActionButtonDocked = false,
        floatingActionButton = { ExtendedFloatingActionButton(
            icon = {
                   Icon(
                       imageVector = Icons.Default.AddCircle,
                       contentDescription = "Add Task"
                   )
            },
            text = { Text(text = "ADD") },
            onClick = { /*TODO*/ })
            
        }
    )
}

@Composable
fun MyTaskWidgetList(listaDetarefas: List<Tarefa>){
    listaDetarefas.forEach(action = {
        Log.i("####%%%%%####", "{$it.nome}")
    })
}

@Composable
fun MySeachFiled(modificador: Modifier){
    TextField(value = "",
        onValueChange = {},
        modifier = modificador,
        placeholder = {Text(text = "Pesquisar tarefas ")},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon")
        })
}

@Composable
fun MyTaskWidget(
        modificador: Modifier,
        tarefaASerMostrada: Tarefa
        /*taskName: String,
        taskDetails: String,
        deadEndDate: Date*/
    ){
    val DateFormatter = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault())
    Row(modifier = modificador){
        Column() {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Icons of a pendent task"
            )
            /*Text(
                text = DateFormatter.format(tarefaASerMostrada.pzoFinal),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 12.sp
            )*/
        }
        Column(modifier = modificador
            .border(width = 1.dp, color = Color.Black)
            .padding(3.dp)
        ){
            /*Text(
                text = tarefaASerMostrada.nome,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = tarefaASerMostrada.detalhes,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )*/
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestTheme {
        MainScreenContent(DrawerState(initialValue = DrawerValue.Closed))
    }
}