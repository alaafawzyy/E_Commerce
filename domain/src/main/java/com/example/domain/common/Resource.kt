package com.example.domain.common

import java.lang.Exception

  sealed class Resource<out T> {
    data class Success<T>(val data:T):Resource<T>()
    data class ServerFail( val error: ServerError):Resource<Nothing>()
    data class Fail(val exception: Throwable):Resource<Nothing>()
    data object loading:Resource<Nothing>()
 }
//<T>
//  وده بيمثل نوع البيانات اللي إحنا بنشتغل عليها أو اللي هنرجعها لما نحمل الداتا زي ما ممكن تكون بيانات من API مثلاً

//Success:
//دي الحالة اللي بتحصل لما الداتا ترجع بنجاح من السيرفر، يعني من غير أي مشاكل. الكلاس Success بياخد باراميتر data اللي هو من النوع <T>.
//ليه بياخد حاجة؟ علشان بنحتاج نخزن الداتا اللي رجعت بنجاح