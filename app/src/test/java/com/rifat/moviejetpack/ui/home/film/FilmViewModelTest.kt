package com.rifat.moviejetpack.ui.home.film

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhaarman.mockitokotlin2.verify
import com.rifat.moviejetpack.data.FakeMovieRepository
import com.rifat.moviejetpack.data.MovieRepository
import com.rifat.moviejetpack.data.entities.GenreEntity
import com.rifat.moviejetpack.data.entities.MovieEntity
import org.junit.Assert

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FilmViewModelTest {
    private lateinit var viewModel: FilmViewModel
    private lateinit var data: String
    private lateinit var genreData: String

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Mock
    private lateinit var observerGenre: Observer<List<GenreEntity>>

    @Before
    fun setUp() {
        viewModel = FilmViewModel(movieRepository)
        data =
            "[{\"adult\":false,\"backdrop_path\":\"\\/6ELCZlTA5lGUops70hKdB83WJxH.jpg\",\"genre_ids\":[28,14,12],\"id\":460465,\"original_language\":\"en\",\"original_title\":\"Mortal Kombat\",\"overview\":\"Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.\",\"popularity\":2112.759,\"poster_path\":\"\\/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg\",\"release_date\":\"2021-04-07\",\"title\":\"Mortal Kombat\",\"video\":false,\"vote_average\":7.6,\"vote_count\":2563},{\"adult\":false,\"backdrop_path\":\"\\/fPGeS6jgdLovQAKunNHX8l0avCy.jpg\",\"genre_ids\":[28,12,53,10752],\"id\":567189,\"original_language\":\"en\",\"original_title\":\"Tom Clancy's Without Remorse\",\"overview\":\"An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.\",\"popularity\":2046.614,\"poster_path\":\"\\/rEm96ib0sPiZBADNKBHKBv5bve9.jpg\",\"release_date\":\"2021-04-29\",\"title\":\"Tom Clancy's Without Remorse\",\"video\":false,\"vote_average\":7.3,\"vote_count\":932},{\"adult\":false,\"backdrop_path\":\"\\/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg\",\"genre_ids\":[878,28,18],\"id\":399566,\"original_language\":\"en\",\"original_title\":\"Godzilla vs. Kong\",\"overview\":\"In a time when monsters walk the Earth, humanity\\u2019s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.\",\"popularity\":1527.021,\"poster_path\":\"\\/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg\",\"release_date\":\"2021-03-24\",\"title\":\"Godzilla vs. Kong\",\"video\":false,\"vote_average\":8.1,\"vote_count\":5581},{\"adult\":false,\"backdrop_path\":\"\\/lkInRiMtLgl9u9xE0By5hqf66K8.jpg\",\"genre_ids\":[27],\"id\":632357,\"original_language\":\"en\",\"original_title\":\"The Unholy\",\"overview\":\"Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.\",\"popularity\":1264.597,\"poster_path\":\"\\/b4gYVcl8pParX8AjkN90iQrWrWO.jpg\",\"release_date\":\"2021-03-31\",\"title\":\"The Unholy\",\"video\":false,\"vote_average\":5.6,\"vote_count\":92},{\"adult\":false,\"backdrop_path\":\"\\/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg\",\"genre_ids\":[28,53,80,35],\"id\":615457,\"original_language\":\"en\",\"original_title\":\"Nobody\",\"overview\":\"Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor \\u2014 a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.\",\"popularity\":1134.22,\"poster_path\":\"\\/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg\",\"release_date\":\"2021-03-26\",\"title\":\"Nobody\",\"video\":false,\"vote_average\":8.5,\"vote_count\":1554},{\"adult\":false,\"backdrop_path\":\"\\/c7dFSqZQYqNNJVpacpIGZe3gkLW.jpg\",\"genre_ids\":[16,35,14],\"id\":813258,\"original_language\":\"en\",\"original_title\":\"Monster Pets: A Hotel Transylvania Short\",\"overview\":\"Drac tries out some new monster pets to help occupy Tinkles for playtime.\",\"popularity\":1097.19,\"poster_path\":\"\\/dkokENeY5Ka30BFgWAqk14mbnGs.jpg\",\"release_date\":\"2021-04-02\",\"title\":\"Monster Pets: A Hotel Transylvania Short\",\"video\":false,\"vote_average\":7.6,\"vote_count\":126},{\"adult\":false,\"backdrop_path\":\"\\/n2y7T8wJVjJ8yLhQXQgNCpsC3ga.jpg\",\"genre_ids\":[10751,16,35],\"id\":811367,\"original_language\":\"en\",\"original_title\":\"22 vs. Earth\",\"overview\":\"Set before the events of \\u2018Soul\\u2019, 22 refuses to go to Earth, enlisting a gang of 5 new souls in attempt of rebellion. However, 22\\u2019s subversive plot leads to a surprising revelation about the meaning of life.\",\"popularity\":1057.223,\"poster_path\":\"\\/32vLDKSzcCMh55zqqaSqqUA8naz.jpg\",\"release_date\":\"2021-04-30\",\"title\":\"22 vs. Earth\",\"video\":false,\"vote_average\":7.1,\"vote_count\":80},{\"adult\":false,\"backdrop_path\":\"\\/ouOojiypBE6CD1aqcHPVq7cJf2R.jpg\",\"genre_ids\":[53,18,28,9648],\"id\":578701,\"original_language\":\"en\",\"original_title\":\"Those Who Wish Me Dead\",\"overview\":\"A young boy finds himself pursued by two assassins in the Montana wilderness with a survival expert determined to protecting him - and a forest fire threatening to consume them all.\",\"popularity\":1446.705,\"poster_path\":\"\\/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg\",\"release_date\":\"2021-05-05\",\"title\":\"Those Who Wish Me Dead\",\"video\":false,\"vote_average\":7.2,\"vote_count\":205},{\"adult\":false,\"backdrop_path\":\"\\/lHhc60NXYzswU4TvKSo45nY9Jzs.jpg\",\"genre_ids\":[16,35,10751,12],\"id\":726684,\"original_language\":\"fr\",\"original_title\":\"Miraculous World Shanghai, la l\\u00e9gende de Ladydragon\",\"overview\":\"To join Adrien in Shanghai, Marinette is going to visit her uncle Wang who is celebrating his anniversary. But, as soon as she arrives in China, her purse gets stolen with Tikki inside, whom she needs to secretly transform into Ladybug! Without money and alone in the immense city, Marinette accepts the help of a young and resourceful girl, Fei. The two girls will ally and discover the existence of a new magical jewel, the Prodigious. Hawk Moth, also present in Shanghai, seeks to finding it since a long time...\",\"popularity\":1073.927,\"poster_path\":\"\\/msI5a9TPnepx47JUb2vl88hb80R.jpg\",\"release_date\":\"2021-04-04\",\"title\":\"Miraculous World: Shanghai \\u2013 The Legend of Ladydragon\",\"video\":false,\"vote_average\":7.9,\"vote_count\":315},{\"adult\":false,\"backdrop_path\":\"\\/xPpXYnCWfjkt3zzE0dpCNME1pXF.jpg\",\"genre_ids\":[16,28,12,14,18],\"id\":635302,\"original_language\":\"ja\",\"original_title\":\"\\u5287\\u5834\\u7248\\u300c\\u9b3c\\u6ec5\\u306e\\u5203\\u300d\\u7121\\u9650\\u5217\\u8eca\\u7de8\",\"overview\":\"Tanjir\\u014d Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Ky\\u014djur\\u014d Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!\",\"popularity\":1122.734,\"poster_path\":\"\\/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg\",\"release_date\":\"2020-10-16\",\"title\":\"Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train\",\"video\":false,\"vote_average\":8.4,\"vote_count\":977},{\"adult\":false,\"backdrop_path\":\"\\/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg\",\"genre_ids\":[28,12,14,878],\"id\":791373,\"original_language\":\"en\",\"original_title\":\"Zack Snyder's Justice League\",\"overview\":\"Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.\",\"popularity\":963.401,\"poster_path\":\"\\/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg\",\"release_date\":\"2021-03-18\",\"title\":\"Zack Snyder's Justice League\",\"video\":false,\"vote_average\":8.5,\"vote_count\":5485},{\"adult\":false,\"backdrop_path\":\"\\/7prYzufdIOy1KCTZKVWpjBFqqNr.jpg\",\"genre_ids\":[16,12,14,10751,28],\"id\":527774,\"original_language\":\"en\",\"original_title\":\"Raya and the Last Dragon\",\"overview\":\"Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it\\u2019s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.\",\"popularity\":860.359,\"poster_path\":\"\\/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg\",\"release_date\":\"2021-03-03\",\"title\":\"Raya and the Last Dragon\",\"video\":false,\"vote_average\":8.2,\"vote_count\":2851},{\"adult\":false,\"backdrop_path\":\"\\/mYM8x2Atv4MaLulaV0KVJWI1Djv.jpg\",\"genre_ids\":[28,80,53],\"id\":804435,\"original_language\":\"en\",\"original_title\":\"Vanquish\",\"overview\":\"Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters\\u2014or she may never see her child again.\",\"popularity\":786.297,\"poster_path\":\"\\/AoWY1gkcNzabh229Icboa1Ff0BM.jpg\",\"release_date\":\"2021-04-16\",\"title\":\"Vanquish\",\"video\":false,\"vote_average\":6.2,\"vote_count\":93},{\"adult\":false,\"backdrop_path\":\"\\/7HtvmsLrDeiAgDGa1W3m6senpfE.jpg\",\"genre_ids\":[12,16,10751],\"id\":681260,\"original_language\":\"en\",\"original_title\":\"Maya the Bee: The Golden Orb\",\"overview\":\"When Maya, a headstrong little bee, and her best friend Willi, rescue an ant princess they find themselves in the middle of an epic bug battle that will take them to strange new worlds and test their friendship to its limits.\",\"popularity\":693.522,\"poster_path\":\"\\/tMS2qcbhbkFpcwLnbUE9o9IK4HH.jpg\",\"release_date\":\"2021-01-07\",\"title\":\"Maya the Bee: The Golden Orb\",\"video\":false,\"vote_average\":6.7,\"vote_count\":29},{\"adult\":false,\"backdrop_path\":\"\\/5Zv5KmgZzdIvXz2KC3n0MyecSNL.jpg\",\"genre_ids\":[28,53,80],\"id\":634528,\"original_language\":\"en\",\"original_title\":\"The Marksman\",\"overview\":\"Jim Hanson\\u2019s quiet life is suddenly disturbed by two people crossing the US\\/Mexico border \\u2013 a woman and her young son \\u2013 desperate to flee a Mexican cartel. After a shootout leaves the mother dead, Jim becomes the boy\\u2019s reluctant defender. He embraces his role as Miguel\\u2019s protector and will stop at nothing to get him to safety, as they go on the run from the relentless assassins.\",\"popularity\":696.054,\"poster_path\":\"\\/6vcDalR50RWa309vBH1NLmG2rjQ.jpg\",\"release_date\":\"2021-01-15\",\"title\":\"The Marksman\",\"video\":false,\"vote_average\":7.4,\"vote_count\":468},{\"adult\":false,\"backdrop_path\":\"\\/jFINtstDUh0vHOGImpMAmLrPcXy.jpg\",\"genre_ids\":[28,27,35],\"id\":643586,\"original_language\":\"en\",\"original_title\":\"Willy's Wonderland\",\"overview\":\"When his car breaks down, a quiet loner agrees to clean an abandoned family fun center in exchange for repairs. He soon finds himself waging war against possessed animatronic mascots while trapped inside Willy's Wonderland.\",\"popularity\":650.944,\"poster_path\":\"\\/keEnkeAvifw8NSEC4f6WsqeLJgF.jpg\",\"release_date\":\"2021-02-12\",\"title\":\"Willy's Wonderland\",\"video\":false,\"vote_average\":6.8,\"vote_count\":216},{\"adult\":false,\"backdrop_path\":\"\\/ovggmAOu1IbPGTQE8lg4lBasNC7.jpg\",\"genre_ids\":[878,28,12,53],\"id\":412656,\"original_language\":\"en\",\"original_title\":\"Chaos Walking\",\"overview\":\"Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.\",\"popularity\":577.486,\"poster_path\":\"\\/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg\",\"release_date\":\"2021-02-24\",\"title\":\"Chaos Walking\",\"video\":false,\"vote_average\":7.1,\"vote_count\":603},{\"adult\":false,\"backdrop_path\":\"\\/z7HLq35df6ZpRxdMAE0qE3Ge4SJ.jpg\",\"genre_ids\":[28,12,35,14],\"id\":615678,\"original_language\":\"en\",\"original_title\":\"Thunder Force\",\"overview\":\"In a world where supervillains are commonplace, two estranged childhood best friends reunite after one devises a treatment that gives them powers to protect their city.\",\"popularity\":605.357,\"poster_path\":\"\\/3mKMWP5OokB7QpcOMA1yl8BXFAF.jpg\",\"release_date\":\"2021-04-09\",\"title\":\"Thunder Force\",\"video\":false,\"vote_average\":5.8,\"vote_count\":577},{\"adult\":false,\"backdrop_path\":\"\\/z8TvnEVRenMSTemxYZwLGqFofgF.jpg\",\"genre_ids\":[14,28,12],\"id\":458576,\"original_language\":\"en\",\"original_title\":\"Monster Hunter\",\"overview\":\"A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.\",\"popularity\":535.543,\"poster_path\":\"\\/1UCOF11QCw8kcqvce8LKOO6pimh.jpg\",\"release_date\":\"2020-12-03\",\"title\":\"Monster Hunter\",\"video\":false,\"vote_average\":7,\"vote_count\":1653},{\"adult\":false,\"backdrop_path\":\"\\/9ns9463dwOeo1CK1JU2wirL5Yi1.jpg\",\"genre_ids\":[35,10751,16],\"id\":587807,\"original_language\":\"en\",\"original_title\":\"Tom & Jerry\",\"overview\":\"Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can\\u2019t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.\",\"popularity\":490.811,\"poster_path\":\"\\/8XZI9QZ7Pm3fVkigWJPbrXCMzjq.jpg\",\"release_date\":\"2021-02-11\",\"title\":\"Tom & Jerry\",\"video\":false,\"vote_average\":7.3,\"vote_count\":1370}]"
        genreData =
            "[{\"id\":28,\"name\":\"Action\"},{\"id\":12,\"name\":\"Adventure\"},{\"id\":16,\"name\":\"Animation\"},{\"id\":35,\"name\":\"Comedy\"},{\"id\":80,\"name\":\"Crime\"},{\"id\":99,\"name\":\"Documentary\"},{\"id\":18,\"name\":\"Drama\"},{\"id\":10751,\"name\":\"Family\"},{\"id\":14,\"name\":\"Fantasy\"},{\"id\":36,\"name\":\"History\"},{\"id\":27,\"name\":\"Horror\"},{\"id\":10402,\"name\":\"Music\"},{\"id\":9648,\"name\":\"Mystery\"},{\"id\":10749,\"name\":\"Romance\"},{\"id\":878,\"name\":\"Science Fiction\"},{\"id\":10770,\"name\":\"TV Movie\"},{\"id\":53,\"name\":\"Thriller\"},{\"id\":10752,\"name\":\"War\"},{\"id\":37,\"name\":\"Western\"}]"
    }

    @Test
    fun getMovies() {
        val gson = Gson()
        val listMovie = object : TypeToken<List<MovieEntity>>() {}.type
        val dummyMovie: List<MovieEntity> = gson.fromJson(data, listMovie)
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovie

        Mockito.`when`(movieRepository.getListMovie()).thenReturn(movies)
        val list = viewModel.getMovies().value
        verify(movieRepository).getListMovie()
        viewModel.getMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
        Assert.assertNotNull(list)
    }

    @Test
    fun getGenres() {
        val gson = Gson()
        val typeGenre = object : TypeToken<List<GenreEntity>>() {}.type
        val dummyGenre: List<GenreEntity> = gson.fromJson(genreData, typeGenre)
        val genres = MutableLiveData<List<GenreEntity>>()
        genres.value = dummyGenre

        Mockito.`when`(movieRepository.getMovieGenre()).thenReturn(genres)
        val list = viewModel.getGenres().value
        verify(movieRepository).getMovieGenre()
        viewModel.getGenres().observeForever(observerGenre)
        Mockito.verify(observerGenre).onChanged(dummyGenre)
        Assert.assertNotNull(list)
    }
}