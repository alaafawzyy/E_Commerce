package com.example.e_commerce_xml.ui.home
import android.app.usage.UsageEvents
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import com.example.domain.model.Category
import com.example.e_commerce_xml.SingleLiveEvent
import com.example.e_commerce_xml.ui.base.ViewMessage
import kotlinx.coroutines.flow.StateFlow

class HomeContract {
    interface ViewModel{
        val state:StateFlow<State>     //مش هخليها live data لان عيزاه يحتفظ بال حاله دي علشان لو محتفظتش بيها وعمل dismiss هيعمل ريلود لل اسكرين تاني
        val event:SingleLiveEvent<Event>
        fun doAction(action: Action)
    }
    sealed class Action{
        data object InitPage:Action()
    }
    sealed class Event{
     data class showMessage(val viewMessage: ViewMessage):Event()

    }
    sealed class State{
        data object Loading:State()
        data class Succes(val categories:List<Category>?=null):State()
    }
}