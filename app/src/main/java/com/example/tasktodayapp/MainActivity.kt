package com.example.tasktodayapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktodayapp.model.Tarefa.Tarefa
import com.example.tasktodayapp.ui.theme.Blue
import com.example.tasktodayapp.ui.theme.Purple200
import com.example.tasktodayapp.ui.theme.TaskTodayAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenContent(DrawerState(initialValue = DrawerValue.Closed))
        }
    }
}

@Composable
fun MainScreenContent(drawerState: DrawerState){
    val scaffoldState = rememberScaffoldState(drawerState = drawerState)
    val scope = rememberCoroutineScope()
    //val tabIndex = by remember {  mutableStateOf(0)  }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = (Color(0xFF0077B6)),
                contentColor = Color.Black,
                title = { Text(text = "TaskTodayApp")},
                navigationIcon = {
                    IconButton(
                        onClick = {
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
        drawerBackgroundColor = (Color(0xFF0077B6)),
        drawerGesturesEnabled = drawerState.isOpen,
        drawerContent = {
            Box(
                modifier = Modifier
                    .background(color = Color(0xFF0077B6))
                    .height(100.dp)
            ){
                Image(
                    painter = painterResource(R.drawable.taf),
                    contentDescription = "Imagem do Menu",
                    modifier = Modifier
                        .size(400.dp)
                        .clip(CircleShape)
                        .scale(1.2f)
                        .fillMaxSize()
                        .padding()

                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(10.dp)
                    .clickable { },
                elevation = 10.dp,
                backgroundColor = (Color(0xFF0096C7))
            ) {
                Column() {
                    Row(modifier = Modifier
                        .padding(5.dp)){
                        Text(text = "MARIA WERLANG", color = Color.White)
                    }
                }

            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(10.dp)
                    .clickable { },
                elevation = 10.dp,
                backgroundColor = (Color(0xFF0096C7))
            ) {
                Column() {
                    Row(modifier = Modifier
                        .padding(5.dp)){
                        Text(text = "RM: 22308", color = Color.White)
                    }
                }

            }
        },
        content = {
                paddingValues -> Log.i("paddingValues", "$paddingValues")
            Column(
                modifier = Modifier
                    .background(color = Color(0xFFADE8F4))
                    .fillMaxSize()
            ) {
                MySearchField(modificador = Modifier.fillMaxWidth())

                val tProvaDeCalculo = Tarefa(
                    "Estudar Prova de Calculo",
                    "Capitulo",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val tProvaDeKotlin = Tarefa(
                    "Estudar Prova de Kotlin",
                    "Capitulo",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val tMudarFundo = Tarefa(
                    "Mudar Fundo",
                    "Mudar cor do fundo do App",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val tNome = Tarefa(
                    "Colocar nome",
                    "Adicionar o meu nome do App",
                    Date(),
                    Date(),
                    status = 0.0
                )

                val tRM = Tarefa(
                    "Colocar RM",
                    "Adicionar o meu RM no App",
                    Date(),
                    Date(),
                    status = 0.0
                )

                var minhaListaDeTarefas = listOf<Tarefa>(tProvaDeCalculo, tProvaDeKotlin, tMudarFundo, tNome, tRM)

                MyTaskWidgetList(minhaListaDeTarefas)
            }//Column
        },//content
        bottomBar = {
            BottomAppBar(
                backgroundColor = (Color(0xFF0077B6)),
                contentColor = Color.White,
                content = { Text(" MARIA WERLANG              RM: 22308")
                }
            )
        },

        isFloatingActionButtonDocked = false,
        floatingActionButton = { ExtendedFloatingActionButton(
            backgroundColor = (Color(0xFF0077B6)),
            contentColor = Color.White,
            icon = {
                   Icon(
                       imageVector = Icons.Default.AddCircle,
                       contentDescription = "Add Task"
                   )
            },
            text = { Text("ADD")  },
            onClick = { /*TODO*/ })

        }
    ) //Scaffold
} //MainScreenContent

@Composable
fun MyTaskWidgetList(listaDeTarefas: List<Tarefa>){
    listaDeTarefas.forEach(
        action = { MyTaskWidget(modificador = Modifier.fillMaxWidth(), taredasASerMostrada = it) }
    )
} //MyTaskWidgetList

@Composable
fun MySearchField(modificador: Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modificador,
        placeholder = { Text(text = "Pesquisar tarefas")},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon")
        }
    )
} //MySearchField

@Composable
fun MyTaskWidget(
    modificador: Modifier,
    taredasASerMostrada: Tarefa
) {
    val dateFormatter = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault())

    Column(modifier = Modifier
        //.border(width = 0.5.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
        .padding(3.dp)
    ) {
        Row(modifier = Modifier.background(Color(0xFF0077B6))) {
            Column(modifier = Modifier
                .width(150.dp)
                .padding(10.dp)) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Icons of a pendent task"
                )
                Text(
                    text = dateFormatter.format(taredasASerMostrada.pzoFinal),
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontSize = 12.sp
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .clickable { },
                elevation = 10.dp,
                backgroundColor = Color(0xFF0077B6)
            ) {
                Column (modifier = Modifier
                    .padding(10.dp)) {
                    Text(
                        text = taredasASerMostrada.nome,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )

                    Text(
                        text = taredasASerMostrada.detalhes,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

        }
        }
        //Spacer(modifier = Modifier.height(16.dp))
        // } //MyTaskWidget


        //@Preview(showBackground = true)
        @Composable
        fun DefaultPreview() {
            MainScreenContent(DrawerState(initialValue = DrawerValue.Closed))
        }
    }