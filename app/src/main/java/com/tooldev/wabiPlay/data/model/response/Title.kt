package com.tooldev.wabiPlay.data.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Title: Serializable {

	@SerializedName("Title")
	val title : String = ""

	@SerializedName("Year")
	val year : String = ""

	@SerializedName("Rated")
	var rated : String = ""

	@SerializedName("Released")
	var released : String = ""

	@SerializedName("Runtime")
	var runtime : String = ""

	@SerializedName("Genre")
	var genre : String = ""

	@SerializedName("Director")
	var director : String = ""

	@SerializedName("Writer")
	var writer : String = ""

	@SerializedName("Actors")
	var actors : String = ""

	@SerializedName("Plot")
	var plot : String = ""

	@SerializedName("Language")
	var language : String = ""

	@SerializedName("Country")
	var country : String = ""

	@SerializedName("Awards")
	var awards : String = ""

	@SerializedName("Poster")
	var poster : String = ""

	@SerializedName("Ratings")
	var ratings : List<Ratings> = listOf()

	@SerializedName("Metascore")
	var metascore : String = ""

	@SerializedName("imdbRating")
	var imdbRating : Double = 0.0

	@SerializedName("imdbVotes")
	var imdbVotes : String = ""

	@SerializedName("imdbID")
	var imdbID : String = ""

	@SerializedName("Type")
	var type : String = ""

	@SerializedName("DVD")
	var dVD : String = ""

	@SerializedName("BoxOffice")
	var boxOffice : String = ""

	@SerializedName("Production")
	var production : String = ""

	@SerializedName("Website")
	var website : String = ""

	@SerializedName("Response")
	var response : Boolean = false
}