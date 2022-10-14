package com.example.jetpackcompose

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.request.transition.Transition
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.fresco.FrescoImage
import com.skydoves.landscapist.glide.GlideImage
import okhttp3.OkHttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            JetpackComposeTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }
//            }

            //LazyColumn - Alternative for Recycler View
            LazyColumn {

                //-----------------Image--------------------
                item {
                    TitleComponent("Load image from the local folder")
                    LocalResourceImageComponent(R.drawable.local)
                    TitleComponent("Image with rounded corners")
                    ImageWithRoundedCorners(R.drawable.local)
                    TitleComponent("Load image from url using Glide")
                    NetworkImageComponentGlide(url = "https://i.picsum.photos/id/866/200/300.jpg?hmac=rcadCENKh4rD6MAp6V_ma-AyWv641M4iiOpe1RyFHeI")
                    TitleComponent("Load image from url using Coil")
                    NetworkImageComponentCoil(url = "https://m.media-amazon.com/images/I/41rb2mpNk+L._AC_UX679_.jpg")
                    TitleComponent("Load image from url using Fresco")
                    NetworkImageComponentFresco(url = "https://m.media-amazon.com/images/I/61vOeoqmGQL._AC_UX466_.jpg")
                }

                //--------------Horizontal Scrolling--------------
                item {
                    TitleComponent("Horizontal Scrolling")
                    HorizontalScrollableComponent(getPersonList())
                    TitleComponent("Horizontal Scrolling Item Full Width")
                    HorizontalScrollableComponentWithScreenWidth(getPersonList())
                }

                //---------------Buttons---------------
                item {
                    TitleComponent("Button : TextButtonComponent")
                    TextButtonComponent()
                    TitleComponent("Button : SimpleButtonComponent")
                    SimpleButtonComponent()
                    TitleComponent("Button : SimpleButtonWithBorderComponent")
                    SimpleButtonWithBorderComponent()
                    TitleComponent("Button : RoundedCornerButtonComponent")
                    RoundedCornerButtonComponent()
                    TitleComponent("Button : OutlinedButtonComponent")
                    OutlinedButtonComponent()
                }

                //---------------Dialog---------------------
                item {
                    TitleComponent("Click to view Alert Dialog")
                    ClickableText()
                }

                //-----------------Edit Text----------------
                item {
                    TitleComponent("Simple Text Input field")
                    SimpleTextInputComponent()
                    TitleComponent("TextInput with custom text style")
                    CustomStyleTextInputComponent()
                    TitleComponent("TextInput suitable for typing numbers")
                    NumberTextInputComponent()
                    TitleComponent("TextInput for Password")
                    PasswordVisualTransformationInputComponent()
                    TitleComponent("TextInput field based on Material Design")
                    MaterialTextInputComponent()
                }

                //------------------Material Designs-------------------
                item {
                    TitleComponent("Simple Material card")
                    MaterialCardComponent()
                    TitleComponent("Loading progress indicator ")
                    MaterialLinearProgressIndicatorComponent()
                    TitleComponent("Circular progress indicator")
                    MaterialCircularProgressIndicatorComponent()
                    TitleComponent("Seekbar/Slider")
                    MaterialContinousSliderComponent()
                    TitleComponent("Checkbox")
                    MaterialCheckboxComponent()
                    TitleComponent("Radio button group")
                    MaterialRadioButtonGroupComponent()
                    TitleComponent("Switch component")
                    MaterialSwitchComponent()
                    TitleComponent("Ripple effect")
                    MaterialRippleComponent()
                }

                //--------------------Buttons Arrangement-----------------
                item {
                    TitleComponent("Views with equal weights")
                    RowEqualWeightComponent()
                    TitleComponent("Views with unequal weights")
                    RowUnequalWeightComponent()
                    TitleComponent("View with auto space in between")
                    RowAddSpaceBetweenViewsComponent()
                    TitleComponent("Views spaced evenly")
                    RowSpaceViewsEvenlyComponent()
                    TitleComponent("Space around child views")
                    RowSpaceAroundViewsComponent()
                    TitleComponent("Views centered")
                    RowViewsCenteredComponent()
                    TitleComponent("Views arranged in end")
                    RowViewsArrangedInEndComponent()
                    TitleComponent("Base of views aligned")
                    RowBaselineAlignComponent()
                    TitleComponent("Base of views not aligned")
                    RowBaselineUnalignedComponent()
                }

                //--------------------Bottom Navigation View-----------------------
                item {
                    TitleComponent("Bottom navigation bar that always shows label")
                    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
                        BottomNavigationAlwaysShowLabelComponent()
                    }
                    TitleComponent("Bottom navigation bar that only shows label for selected item")
                    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
                        BottomNavigationOnlySelectedLabelComponent()
                    }
                }
            }
        }

        val pipelineConfig =
            OkHttpImagePipelineConfigFactory
                .newBuilder(this, OkHttpClient.Builder().build())
                .setDiskCacheEnabled(true)
                .setDownsampleEnabled(true)
                .setResizeAndRotateEnabledForNetwork(true)
                .build()

        Fresco.initialize(this, pipelineConfig)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
        Greeting("Android")
    }
}


@Composable
fun LocalResourceImageComponent(@DrawableRes resId: Int) {
    val image = painterResource(resId)
    Image(painter = image, contentDescription = null, modifier = Modifier.sizeIn(maxHeight = 200.dp).fillMaxWidth())
}
@Composable
fun ImageWithRoundedCorners(@DrawableRes resId: Int) {
    val image = painterResource(resId)
    Column(modifier = Modifier.clip(RoundedCornerShape(8.dp)), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = image, modifier = Modifier.height(200.dp), contentDescription = null)
    }
}
@Composable
fun NetworkImageComponentGlide(url: String) {
    GlideImage(imageModel = { url },
        imageOptions = ImageOptions(contentScale = ContentScale.Inside, alignment = Alignment.Center)
    )
}
@Composable
fun NetworkImageComponentCoil(url: String) {
    CoilImage(imageModel = { url },
        imageOptions = ImageOptions(contentScale = ContentScale.Inside, alignment = Alignment.Center)
    )
}
@Composable
fun NetworkImageComponentFresco(url: String) {
    FrescoImage(imageUrl = url,
        imageOptions = ImageOptions(contentScale = ContentScale.Inside, alignment = Alignment.Center)
    )
}


@Composable
fun TitleComponent(title: String) {

    Text(title, style = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W900, fontSize = 14.sp, color = Color.Black),
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    )
}


@Composable
fun HorizontalScrollableComponent(personList: List<Person>) {
    val scrollState = rememberScrollState()
    Row(modifier = Modifier.fillMaxWidth().horizontalScroll(state = scrollState,), content = {
        for ((index, person) in personList.withIndex()) {
            Card(shape = RoundedCornerShape(4.dp), backgroundColor = colors[index % colors.size], modifier = Modifier.padding(16.dp))
            {
                Text(person.name, modifier = Modifier.padding(16.dp), style = TextStyle(color = Color.Black, fontSize = 20.sp))
            }
        }
    })
}
@Composable
fun HorizontalScrollableComponentWithScreenWidth(personList: List<Person>) {
    val scrollState = rememberScrollState()
    Row(modifier = Modifier.fillMaxWidth().horizontalScroll(state = scrollState,), content = {
        val context = LocalContext.current
        val resources = context.resources
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels / displayMetrics.density
        val spacing = 16.dp
        Row {
            for ((index, person) in personList.withIndex()) {
                Card(shape = RoundedCornerShape(4.dp), backgroundColor = colors[index % colors.size], modifier = Modifier.padding(16.dp))
                {
                    Column(modifier = Modifier.width(screenWidth.dp - (spacing * 2)), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Text(text = person.name, modifier = Modifier.padding(16.dp), style = TextStyle(color = Color.Black, fontSize = 20.sp)
                        )
                    }
                }
            }
        }
    })
}


@Composable
fun TextButtonComponent() {
    val context = LocalContext.current
    TextButton(onClick = {Toast.makeText(context,"TextButton",Toast.LENGTH_SHORT).show()}, modifier = Modifier.padding(16.dp)) {
        Text(text = "Text Button", modifier = Modifier.padding(16.dp))
    }
}
@Composable
fun SimpleButtonComponent() {
    val context = LocalContext.current
    Button(onClick = {Toast.makeText(context,"SimpleButton",Toast.LENGTH_SHORT).show()}, modifier = Modifier.padding(16.dp), elevation = ButtonDefaults.elevation(5.dp)) {
        Text(text = "Simple button", modifier = Modifier.padding(16.dp))
    }
}
@Composable
fun SimpleButtonWithBorderComponent() {
    val context = LocalContext.current
    Button(onClick = {Toast.makeText(context,"SimpleButtonWithBorder",Toast.LENGTH_SHORT).show()}, modifier = Modifier.padding(16.dp), elevation = ButtonDefaults.elevation(5.dp), border = BorderStroke(width = 5.dp, brush = SolidColor(Color.Black))) {
        Text(text = "Simple button with border", modifier = Modifier.padding(16.dp))
    }
}
@Composable
fun RoundedCornerButtonComponent() {
    val context = LocalContext.current
    Button(onClick = {Toast.makeText(context,"RoundedCornerButton",Toast.LENGTH_SHORT).show()}, modifier = Modifier.padding(16.dp), shape = RoundedCornerShape(16.dp), elevation = ButtonDefaults.elevation(5.dp),) {
        Text(text = "Button with rounded corners", modifier = Modifier.padding(16.dp))
    }
}
@Composable
fun OutlinedButtonComponent() {
    val context = LocalContext.current
    OutlinedButton(onClick = {Toast.makeText(context,"OutlinedButton",Toast.LENGTH_SHORT).show()}, modifier = Modifier.padding(16.dp)) {
        Text(text = "Outlined Button", modifier = Modifier.padding(16.dp))
    }
}


@Composable
fun ClickableText() {
    var showPopup by remember { mutableStateOf(false) }
    Column(Modifier.clickable(onClick = { showPopup = true }), content = {
        Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp), backgroundColor = Color.LightGray) {
            Text(text = "Click to see dialog", modifier = Modifier.padding(16.dp), style = TextStyle(fontSize = 16.sp, fontFamily = FontFamily.Serif))
        }
    })

    val onPopupDismissed = { showPopup = false }

    if (showPopup) {
        AlertDialog(onDismissRequest = onPopupDismissed, text = { Text("You clicked the text") },
            confirmButton = { Button(onClick = onPopupDismissed) {
                    Text(text = "Ok")
                }
            })
    }
}
@Composable
fun SimpleTextInputComponent() {
    Surface(color = Color.LightGray, modifier = Modifier.padding(16.dp)) {
        var textValue by remember { mutableStateOf(TextFieldValue("Enter your text here")) }
        BasicTextField(value = textValue, modifier = Modifier.padding(16.dp).fillMaxWidth(), onValueChange = { textValue = it })
    }
}
@Composable
fun CustomStyleTextInputComponent() {
    Surface(color = Color.LightGray, modifier = Modifier.padding(16.dp)) {
        var textValue by remember { mutableStateOf(TextFieldValue("Hi its custom style")) }
        BasicTextField(value = textValue, modifier = Modifier.padding(16.dp).fillMaxWidth(),
            textStyle = TextStyle(color = Color.Blue, fontSize = 20.sp, fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline),
            onValueChange = { textValue = it })
    }
}
@Composable
fun NumberTextInputComponent() {
    Surface(color = Color.LightGray, modifier = Modifier.padding(16.dp)) {
        var textValue by remember { mutableStateOf(TextFieldValue("123")) }
        BasicTextField(value = textValue, modifier = Modifier.padding(16.dp).fillMaxWidth(), onValueChange = { textValue = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
    }
}
@Composable
fun PasswordVisualTransformationInputComponent() {
    Surface(color = Color.LightGray, modifier = Modifier.padding(16.dp)) {
        var textValue by remember { mutableStateOf(TextFieldValue("Enter your password here")) }
        BasicTextField(value = textValue, modifier = Modifier.padding(16.dp).fillMaxWidth(), visualTransformation = PasswordVisualTransformation(),
            onValueChange = { textValue = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))
    }
}
@Composable
fun MaterialTextInputComponent() {
    var textValue by remember { mutableStateOf(TextFieldValue("")) }
    TextField(value = textValue, onValueChange = { textValue = it }, label = { Text("Enter Your Name") }, placeholder = { Text(text = "Vivek Happy") },
        modifier = Modifier.padding(16.dp).fillMaxWidth())
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MaterialCardComponent() {
    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
        ListItem(text = { Text(text = "Vivek") }, secondaryText = { Text(text = "Associate Android Developer") },
            icon = { Column(modifier = Modifier.width(56.dp).height(56.dp)) {
                Image(painter = painterResource(R.drawable.local), contentDescription = "Landscape")
            }
        })
    }
}
@Composable
fun MaterialLinearProgressIndicatorComponent() {
    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp)) {
            LinearProgressIndicator()
        }
    }
}
@Composable
fun MaterialCircularProgressIndicatorComponent() {
    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
    }
}
@Composable
fun MaterialContinousSliderComponent() {
    var sliderValue by remember { mutableStateOf(0f) }
    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
        Slider(value = sliderValue, onValueChange = { newValue -> sliderValue = newValue })
    }
}
@Composable
fun MaterialCheckboxComponent() {
    var checked by remember { mutableStateOf(false) }
    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp)) {
            Checkbox(checked = checked, onCheckedChange = { checked = !checked })
            Text(text = "Use Jetpack Compose", modifier = Modifier.padding(start = 8.dp))
        }
    }
}
@Composable
fun MaterialRadioButtonGroupComponent() {
    var selected by remember { mutableStateOf("Android") }
    val radioGroupOptions = listOf("Android", "iOS", "Windows")
    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        val onSelectedChange = { text: String -> selected = text }
        Column {
            radioGroupOptions.forEach { text ->
                Row {
                    RadioButton(selected = (text == selected), onClick = { onSelectedChange(text) })
                    Text(text = text, style = MaterialTheme.typography.body1.merge(), modifier = Modifier.padding(start = 16.dp))
                }
            }
        }
    }
}
@Composable
fun MaterialSwitchComponent() {
    var checked by remember { mutableStateOf(false) }
    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp).fillMaxWidth(), backgroundColor = Color(249, 249, 249)) {
        Row(modifier = Modifier.padding(16.dp)) {
            Switch(checked = checked, onCheckedChange = { checked = !checked })
            Text(text = "Enable", modifier = Modifier.padding(start = 8.dp))
        }
    }
}
@Composable
fun MaterialRippleComponent() {
    Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp).fillMaxWidth(), backgroundColor = Color(249, 249, 249)) {
        Column(modifier = Modifier.clickable(onClick = {}).padding(16.dp).background(color = Color.LightGray, shape = RoundedCornerShape(4.dp))) {
            Text(text = "Click Me", modifier = Modifier.padding(16.dp), style = TextStyle(fontSize = 12.sp, fontFamily = FontFamily.Monospace))
        }
    }
}


@Composable
fun RowEqualWeightComponent() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Button(modifier = Modifier.weight(1f).padding(4.dp), onClick = {}) {
            Text(text = "Button 1", style = TextStyle(fontSize = 20.sp))
        }

        Button(modifier = Modifier.weight(1f).padding(4.dp), onClick = {}) {
            Text(text = "Button 2", style = TextStyle(fontSize = 20.sp))
        }
    }
}
@Composable
fun RowUnequalWeightComponent() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Button(modifier = Modifier.weight(0.66f).padding(4.dp), onClick = {}) {
            Text(text = "Button 1", style = TextStyle(fontSize = 20.sp))
        }

        Button(modifier = Modifier.weight(0.34f).padding(4.dp), onClick = {}) {
            Text(text = "Button 2", style = TextStyle(fontSize = 20.sp))
        }
    }
}
@Composable
fun RowAddSpaceBetweenViewsComponent() {
    Row(modifier = Modifier.fillMaxWidth().padding(4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Button(onClick = {}) {
            Text(text = "Button 1", style = TextStyle(fontSize = 20.sp))
        }

        Button(onClick = {}) {
            Text(text = "Button 2", style = TextStyle(fontSize = 20.sp))
        }
    }
}
@Composable
fun RowSpaceViewsEvenlyComponent() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = {}) {
            Text(text = "Button 1", style = TextStyle(fontSize = 20.sp))
        }

        Button(onClick = {}) {
            Text(text = "Button 2", style = TextStyle(fontSize = 20.sp))
        }
    }
}
@Composable
fun RowSpaceAroundViewsComponent() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        Button(onClick = {}) {
            Text(text = "Button 1", style = TextStyle(fontSize = 20.sp))
        }

        Button(onClick = {}) {
            Text(text = "Button 2", style = TextStyle(fontSize = 20.sp))
        }
    }
}
@Composable
fun RowViewsCenteredComponent() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(onClick = {}, modifier = Modifier.padding(4.dp)) {
            Text(text = "Button 1", style = TextStyle(fontSize = 20.sp))
        }

        Button(onClick = {}, modifier = Modifier.padding(4.dp)) {
            Text(text = "Button 2", style = TextStyle(fontSize = 20.sp))
        }
    }
}
@Composable
fun RowViewsArrangedInEndComponent() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Button(onClick = {}, modifier = Modifier.padding(4.dp)) {
            Text(text = "Button 1", style = TextStyle(fontSize = 20.sp))
        }

        Button(onClick = {}, modifier = Modifier.padding(4.dp)) {
            Text(text = "Button 2", style = TextStyle(fontSize = 20.sp))
        }
    }
}
@Composable
fun RowBaselineAlignComponent() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Text 1", style = TextStyle(fontSize = 20.sp, fontStyle = FontStyle.Italic), modifier = Modifier.alignBy(alignmentLine = FirstBaseline))
        Text(text = "Text 2", style = TextStyle(fontSize = 40.sp, fontStyle = FontStyle.Normal, fontWeight = FontWeight.Bold), modifier = Modifier.alignBy(alignmentLine = FirstBaseline))
    }
}
@Composable
fun RowBaselineUnalignedComponent() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Text 1", style = TextStyle(fontSize = 20.sp, fontStyle = FontStyle.Italic))
        Text(text = "Text 2", style = TextStyle(fontSize = 40.sp, fontStyle = FontStyle.Normal, fontWeight = FontWeight.Bold))
    }
}


val listItems = listOf("Home", "Doubts", "My Content", "Store")
@Composable
fun BottomNavigationAlwaysShowLabelComponent() {
    var selectedIndex by remember { mutableStateOf(0) }
    BottomNavigation(modifier = Modifier.padding(16.dp)) {
        listItems.forEachIndexed { index, label ->
            BottomNavigationItem(icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite") }, label = { Text(text = label) },
                selected = selectedIndex == index, onClick = { selectedIndex = index })
        }
    }
}
@Composable
fun BottomNavigationOnlySelectedLabelComponent() {
    var selectedIndex by remember { mutableStateOf(0) }
    BottomNavigation(modifier = Modifier.padding(16.dp)) {
        listItems.forEachIndexed { index, label ->
            BottomNavigationItem(icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite") }, label = { Text(text = label) },
                selected = selectedIndex == index, onClick = { selectedIndex = index }, alwaysShowLabel = false
            )
        }
    }
}





@Preview("Load image stored in local resources folder")
@Composable
fun LocalResourceImageComponentPreview() {
    Column {
        LocalResourceImageComponent(R.drawable.local)
    }
}

@Preview("Add round corners to an image")
@Composable
fun ImageWithRoundedCornersPreview() {
    ImageWithRoundedCorners(R.drawable.local)
}

@Preview("Load an image over the network using the Glide library")
@Composable
fun NetworkImageComponentGlidePreview() {
    NetworkImageComponentGlide("https://i.picsum.photos/id/866/200/300.jpg?hmac=rcadCENKh4rD6MAp6V_ma-AyWv641M4iiOpe1RyFHeI")
}