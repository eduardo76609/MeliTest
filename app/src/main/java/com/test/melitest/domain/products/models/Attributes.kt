package com.test.melitest.domain.products.models

import com.google.gson.annotations.SerializedName

data class Attributes (

    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("value_id") val value_id : Int,
    @SerializedName("value_name") val value_name : String,
    @SerializedName("attribute_group_id") val attribute_group_id : String,
    @SerializedName("attribute_group_name") val attribute_group_name : String,
    //@SerializedName("values") val values : List<Values>,
    //@SerializedName("source") val source : Int,
    @SerializedName("value_type") val value_type : String
)