package com.rifat.moviejetpack.ui.detail_series

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.rifat.moviejetpack.data.repository.SeriesRepository
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity
import com.rifat.moviejetpack.data.source.remote.responses.DetailSeriesResponse
import com.rifat.moviejetpack.data.source.remote.responses.SeriesResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailSeriesViewModelTest {

    private lateinit var viewModel: DetailSeriesViewModel
    private lateinit var data: String
    private lateinit var dataRelated: String
    private lateinit var seriesFavEntity : FavEntity

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var seriesRepository: SeriesRepository

    @Mock
    private lateinit var observer: Observer<DetailSeriesResponse>

    @Mock
    private lateinit var observerList: Observer<List<SeriesResponse>>

    @Mock
    private lateinit var observerMap: Observer<Map<String, Any>>

    @Mock
    private lateinit var observerFav: Observer<FavEntity>

    @Before
    fun setUp() {
        viewModel = DetailSeriesViewModel(seriesRepository)
        dataRelated =
            "[{\"backdrop_path\":\"\\/dYvIUzdh6TUv4IFRq8UBkX7bNNu.jpg\",\"first_air_date\":\"2021-03-24\",\"genre_ids\":[18,80,9648],\"id\":120168,\"name\":\"Who Killed Sara?\",\"origin_country\":[\"MX\"],\"original_language\":\"es\",\"original_name\":\"\\u00bfQui\\u00e9n mat\\u00f3 a Sara?\",\"overview\":\"Hell-bent on exacting revenge and proving he was framed for his sister's murder, \\u00c1lex sets out to unearth much more than the crime's real culprit.\",\"popularity\":1606.074,\"poster_path\":\"\\/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg\",\"vote_average\":7.8,\"vote_count\":658},{\"backdrop_path\":\"\\/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg\",\"first_air_date\":\"2014-10-07\",\"genre_ids\":[18,10765],\"id\":60735,\"name\":\"The Flash\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"The Flash\",\"overview\":\"After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.\",\"popularity\":1136.942,\"poster_path\":\"\\/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg\",\"vote_average\":7.7,\"vote_count\":7659},{\"backdrop_path\":\"\\/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg\",\"first_air_date\":\"2017-09-25\",\"genre_ids\":[18],\"id\":71712,\"name\":\"The Good Doctor\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"The Good Doctor\",\"overview\":\"A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives\",\"popularity\":1022.301,\"poster_path\":\"\\/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg\",\"vote_average\":8.6,\"vote_count\":8448},{\"backdrop_path\":\"\\/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg\",\"first_air_date\":\"2021-03-19\",\"genre_ids\":[10765,10759,18,10768],\"id\":88396,\"name\":\"The Falcon and the Winter Soldier\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"The Falcon and the Winter Soldier\",\"overview\":\"Following the events of \\u201cAvengers: Endgame\\u201d, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.\",\"popularity\":1048.785,\"poster_path\":\"\\/6kbAMLteGO8yyewYau6bJ683sw7.jpg\",\"vote_average\":7.9,\"vote_count\":5586},{\"backdrop_path\":\"\\/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg\",\"first_air_date\":\"2005-03-27\",\"genre_ids\":[18],\"id\":1416,\"name\":\"Grey's Anatomy\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Grey's Anatomy\",\"overview\":\"Follows the personal and professional lives of a group of doctors at Seattle\\u2019s Grey Sloan Memorial Hospital.\",\"popularity\":836.668,\"poster_path\":\"\\/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg\",\"vote_average\":8.2,\"vote_count\":6083},{\"backdrop_path\":\"\\/nBrkOZyI75artyizuBFeya48KbO.jpg\",\"first_air_date\":\"2019-03-15\",\"genre_ids\":[16,10765],\"id\":86831,\"name\":\"Love, Death & Robots\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Love, Death & Robots\",\"overview\":\"Terrifying creatures, wicked surprises and dark comedy converge in this NSFW anthology of animated stories presented by Tim Miller and David Fincher.\",\"popularity\":761.406,\"poster_path\":\"\\/asDqvkE66EegtKJJXIRhBJPxscr.jpg\",\"vote_average\":8.2,\"vote_count\":1066},{\"backdrop_path\":\"\\/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg\",\"first_air_date\":\"2021-03-26\",\"genre_ids\":[16,10759,18,10765],\"id\":95557,\"name\":\"Invincible\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Invincible\",\"overview\":\"Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father\\u2019s tutelage.\",\"popularity\":890.964,\"poster_path\":\"\\/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg\",\"vote_average\":8.9,\"vote_count\":1759},{\"backdrop_path\":\"\\/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg\",\"first_air_date\":\"2016-01-25\",\"genre_ids\":[80,10765],\"id\":63174,\"name\":\"Lucifer\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Lucifer\",\"overview\":\"Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals.\\u00a0But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.\",\"popularity\":734.614,\"poster_path\":\"\\/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg\",\"vote_average\":8.5,\"vote_count\":8620},{\"backdrop_path\":\"\\/4YKkS95v9o9c0tBVA46xIn6M1tx.jpg\",\"first_air_date\":\"2021-05-07\",\"genre_ids\":[10765,10759,18,9648],\"id\":93484,\"name\":\"Jupiter's Legacy\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Jupiter's Legacy\",\"overview\":\"When the world's first generation of superheroes received their powers in the 1930s become the revered elder guard in the present, their superpowered children struggle to live up to the legendary feats of their parents.\",\"popularity\":689.008,\"poster_path\":\"\\/9yxep7oJdkj3Pla9TD9gKflRApY.jpg\",\"vote_average\":7.4,\"vote_count\":231},{\"backdrop_path\":\"\\/sjxtIUCWR74yPPcZFfTsToepfWm.jpg\",\"first_air_date\":\"2021-05-04\",\"genre_ids\":[10765,10759,16],\"id\":105971,\"name\":\"The Bad Batch\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"The Bad Batch\",\"overview\":\"Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars.\",\"popularity\":648.637,\"poster_path\":\"\\/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg\",\"vote_average\":8.9,\"vote_count\":195},{\"backdrop_path\":\"\\/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg\",\"first_air_date\":\"2017-01-26\",\"genre_ids\":[9648,18,80],\"id\":69050,\"name\":\"Riverdale\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Riverdale\",\"overview\":\"Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale\\u2019s wholesome facade.\",\"popularity\":703.146,\"poster_path\":\"\\/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg\",\"vote_average\":8.6,\"vote_count\":11292},{\"backdrop_path\":\"\\/pPKiIJEEcV0E1hpVcWRXyp73ZpX.jpg\",\"first_air_date\":\"2021-02-23\",\"genre_ids\":[10759,10765,18],\"id\":95057,\"name\":\"Superman & Lois\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Superman & Lois\",\"overview\":\"After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.\",\"popularity\":544.448,\"poster_path\":\"\\/vlv1gn98GqMnKHLSh0dNciqGfBl.jpg\",\"vote_average\":8.3,\"vote_count\":837},{\"backdrop_path\":\"\\/pnyT1foDmmXTsho2DfxN2ePI8ix.jpg\",\"first_air_date\":\"2018-06-12\",\"genre_ids\":[18],\"id\":80240,\"name\":\"The Queen of Flow\",\"origin_country\":[\"CO\"],\"original_language\":\"es\",\"original_name\":\"La Reina del Flow\",\"overview\":\"After spending seventeen years in prison unfairly, a talented songwriter seeks revenge on the men who sank her and killed her family.\",\"popularity\":530.036,\"poster_path\":\"\\/fuVuDYrs8sxvEolnYr0wCSvtyTi.jpg\",\"vote_average\":8,\"vote_count\":768},{\"backdrop_path\":\"\\/hNiGqLsiD30C194lci7VYDmciHD.jpg\",\"first_air_date\":\"2017-04-26\",\"genre_ids\":[10765,18],\"id\":69478,\"name\":\"The Handmaid's Tale\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"The Handmaid's Tale\",\"overview\":\"Set in a dystopian future, a woman is forced to live as a concubine under a fundamentalist theocratic dictatorship. A TV adaptation of Margaret Atwood's novel.\",\"popularity\":478.989,\"poster_path\":\"\\/oIkxqt6ug5zT5ZSUUyc1Iqopf02.jpg\",\"vote_average\":8.2,\"vote_count\":1362},{\"backdrop_path\":\"\\/suopoADq0k8YZr4dQXcU6pToj6s.jpg\",\"first_air_date\":\"2011-04-17\",\"genre_ids\":[10765,18,10759],\"id\":1399,\"name\":\"Game of Thrones\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Game of Thrones\",\"overview\":\"Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.\",\"popularity\":512.071,\"poster_path\":\"\\/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg\",\"vote_average\":8.4,\"vote_count\":14455},{\"backdrop_path\":\"\\/wkyzeBBKLhSg1Oqhky5yoiFF2hG.jpg\",\"first_air_date\":\"2018-04-22\",\"genre_ids\":[18],\"id\":79008,\"name\":\"Luis Miguel: The Series\",\"origin_country\":[\"MX\"],\"original_language\":\"es\",\"original_name\":\"Luis Miguel: La Serie\",\"overview\":\"The series dramatizes the life story of Mexican superstar singer Luis Miguel, who has captivated audiences in Latin America and beyond for decades.\",\"popularity\":472.921,\"poster_path\":\"\\/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg\",\"vote_average\":8.1,\"vote_count\":421},{\"backdrop_path\":\"\\/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg\",\"first_air_date\":\"2015-08-23\",\"genre_ids\":[10759,18],\"id\":62286,\"name\":\"Fear the Walking Dead\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Fear the Walking Dead\",\"overview\":\"What did the world look like as it was transforming into the horrifying apocalypse depicted in \\\"The Walking Dead\\\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.\",\"popularity\":445.739,\"poster_path\":\"\\/4UjiPdFKJGJYdxwRs2Rzg7EmWqr.jpg\",\"vote_average\":7.6,\"vote_count\":3546},{\"backdrop_path\":\"\\/fRYwdeNjMqC30EhofPx5PlDpdun.jpg\",\"first_air_date\":\"2018-10-25\",\"genre_ids\":[10765,18],\"id\":79460,\"name\":\"Legacies\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Legacies\",\"overview\":\"In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson\\u2019s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman\\u2019s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted.\",\"popularity\":468.831,\"poster_path\":\"\\/qTZIgXrBKURBK1KrsT7fe3qwtl9.jpg\",\"vote_average\":8.6,\"vote_count\":1878},{\"backdrop_path\":\"\\/uro2Khv7JxlzXtLb8tCIbRhkb9E.jpg\",\"first_air_date\":\"2010-10-31\",\"genre_ids\":[10759,18,10765],\"id\":1402,\"name\":\"The Walking Dead\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"The Walking Dead\",\"overview\":\"Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.\",\"popularity\":430.198,\"poster_path\":\"\\/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg\",\"vote_average\":8.1,\"vote_count\":10831},{\"backdrop_path\":\"\\/Wu8kh7oyvaIfkNyMJyJHCamh5L.jpg\",\"first_air_date\":\"2020-12-04\",\"genre_ids\":[18],\"id\":97180,\"name\":\"Selena: The Series\",\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Selena: The Series\",\"overview\":\"As Mexican-American Tejano singer Selena comes of age and realizes her dreams, she and her family make tough choices to hold on to love and music.\",\"popularity\":434.774,\"poster_path\":\"\\/mYsWyfiIMxx4HDm0Wck7oJ9ckez.jpg\",\"vote_average\":7.5,\"vote_count\":1331}]"
        data =
            "{\"backdrop_path\":\"\\/h48Dpb7ljv8WQvVdyFWVLz64h4G.jpg\",\"created_by\":[{\"id\":1222585,\"credit_id\":\"55fdc50ec3a368132a001852\",\"name\":\"Tom Kapinos\",\"gender\":2,\"profile_path\":\"\\/ol7GfeO0OIDCWGYzlg1LDLmwluO.jpg\"}],\"episode_run_time\":[45],\"first_air_date\":\"2016-01-25\",\"genres\":[{\"id\":80,\"name\":\"Crime\"},{\"id\":10765,\"name\":\"Sci-Fi & Fantasy\"}],\"homepage\":\"https:\\/\\/www.netflix.com\\/title\\/80057918\",\"id\":63174,\"in_production\":true,\"languages\":[\"en\"],\"last_air_date\":\"2021-05-28\",\"last_episode_to_air\":{\"air_date\":\"2021-05-28\",\"episode_number\":16,\"id\":2856945,\"name\":\"A Chance at a Happy Ending\",\"overview\":\"The end is nigh! Lucifer, Chloe, Maze and Amenadiel prepare for battle with Michael and his not-so-angelic army of supporters.\",\"production_code\":\"\",\"season_number\":5,\"still_path\":\"\\/cYY0U8DAkCRAWO6rnIcZ2gW17Fz.jpg\",\"vote_average\":10,\"vote_count\":1},\"name\":\"Lucifer\",\"next_episode_to_air\":{\"air_date\":\"2022-01-10\",\"episode_number\":1,\"id\":2910283,\"name\":\"Nothing Ever Changes Around Here\",\"overview\":\"\",\"production_code\":\"\",\"season_number\":6,\"still_path\":null,\"vote_average\":0,\"vote_count\":0},\"networks\":[{\"name\":\"FOX\",\"id\":19,\"logo_path\":\"\\/1DSpHrWyOORkL9N2QHX7Adt31mQ.png\",\"origin_country\":\"US\"},{\"name\":\"Netflix\",\"id\":213,\"logo_path\":\"\\/wwemzKWzjKYJFfCeiB57q3r4Bcm.png\",\"origin_country\":\"\"}],\"number_of_episodes\":93,\"number_of_seasons\":6,\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Lucifer\",\"overview\":\"Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals.\\u00a0But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.\",\"popularity\":1464.514,\"poster_path\":\"\\/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg\",\"production_companies\":[{\"id\":43346,\"logo_path\":null,\"name\":\"Fox Productions\",\"origin_country\":\"US\"},{\"id\":1957,\"logo_path\":\"\\/3T19XSr6yqaLNK8uJWFImPgRax0.png\",\"name\":\"Warner Bros. Television\",\"origin_country\":\"US\"},{\"id\":57542,\"logo_path\":null,\"name\":\"Aggressive Mediocrity\",\"origin_country\":\"US\"},{\"id\":9993,\"logo_path\":\"\\/2Tc1P3Ac8M479naPp1kYT3izLS5.png\",\"name\":\"DC Entertainment\",\"origin_country\":\"US\"},{\"id\":40041,\"logo_path\":\"\\/oP8TmVSh9DCP1yhR2yvjnKfMgbg.png\",\"name\":\"Jerry Bruckheimer Television\",\"origin_country\":\"US\"}],\"production_countries\":[{\"iso_3166_1\":\"US\",\"name\":\"United States of America\"}],\"seasons\":[{\"air_date\":\"2015-07-10\",\"episode_count\":4,\"id\":70781,\"name\":\"Specials\",\"overview\":\"\",\"poster_path\":\"\\/bQ5FupU7DFTbx9pSgPsEZQwyZKj.jpg\",\"season_number\":0},{\"air_date\":\"2016-01-25\",\"episode_count\":13,\"id\":68415,\"name\":\"Season 1\",\"overview\":\"Bored with being the Lord of Hell, the devil relocates to Los Angeles, where he opens a nightclub and forms a connection with a homicide detective.\",\"poster_path\":\"\\/9qvNXKYqZEsYn3g3yn5tXQe0ceB.jpg\",\"season_number\":1},{\"air_date\":\"2016-09-19\",\"episode_count\":18,\"id\":78529,\"name\":\"Season 2\",\"overview\":\"Lucifer returns for another season, but his devil-may-care attitude may soon need an adjustment: His mother is coming to town.\",\"poster_path\":\"\\/fTQzbse8HKh0z6UJbMUumdbZ8PX.jpg\",\"season_number\":2},{\"air_date\":\"2017-10-02\",\"episode_count\":26,\"id\":91441,\"name\":\"Season 3\",\"overview\":\"As Lucifer struggles with an identity crisis, a gruff new police lieutenant shakes up the status quo with Chloe and the rest of the LAPD.\",\"poster_path\":\"\\/4mKbrTqGg1daz3pDUgqd9ZSdZRt.jpg\",\"season_number\":3},{\"air_date\":\"2019-05-08\",\"episode_count\":10,\"id\":117593,\"name\":\"Season 4\",\"overview\":\"As Chloe struggles to come to terms with Lucifer's disturbing revelation, a rogue priest sets out to stop a long-rumored prophecy.\",\"poster_path\":\"\\/k9sLJE5geAmOCXOCbKnhSNND60J.jpg\",\"season_number\":4},{\"air_date\":\"2020-08-21\",\"episode_count\":16,\"id\":152759,\"name\":\"Season 5\",\"overview\":\"Lucifer makes a tumultuous return to the land of the living in hopes of making things right with Chloe. A devil\\u2019s work is never done.\",\"poster_path\":\"\\/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg\",\"season_number\":5},{\"air_date\":\"2022-01-10\",\"episode_count\":10,\"id\":192616,\"name\":\"Season 6\",\"overview\":\"\",\"poster_path\":null,\"season_number\":6}],\"spoken_languages\":[{\"english_name\":\"English\",\"iso_639_1\":\"en\",\"name\":\"English\"}],\"status\":\"Returning Series\",\"tagline\":\"It's good to be bad.\",\"type\":\"Scripted\",\"vote_average\":8.5,\"vote_count\":9149}"
        seriesFavEntity = FavEntity(
            "s-84958",
            "Loki",
            "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
            "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
            "2021-06-09",
            8.1,
            "{\"id\":84958,\"name\":\"Loki\",\"overview\":\"After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.\",\"original_language\":\"en\",\"poster_path\":\"/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg\",\"genres\":[{\"id\":18,\"name\":\"Drama\"},{\"id\":10765,\"name\":\"Sci-Fi \\u0026 Fantasy\"}],\"backdrop_path\":\"/ykElAtsOBoArgI1A8ATVH0MNve0.jpg\",\"first_air_date\":\"2021-06-09\",\"vote_average\":8.1,\"adult\":false,\"homepage\":\"https://www.disneyplus.com/series/wp/6pARMvILBGzF\",\"production_companies\":[{\"logo_path\":\"/hUzeosd33nzE5MCNsZxCGEKTXaQ.png\",\"name\":\"Marvel Studios\"}],\"tagline\":\"Loki\\u0027s time has come.\",\"number_of_episodes\":6,\"number_of_seasons\":1,\"networks\":[{\"name\":\"Disney+\",\"logo_path\":\"/gJ8VX6JSu3ciXHuC2dDGAo2lvwM.png\"}],\"seasons\":[{\"air_date\":\"2021-06-09\",\"episode_count\":6,\"name\":\"Season 1\",\"overview\":\"\",\"poster_path\":\"/8uVqe9ThcuYVNdh4O0kuijIWMLL.jpg\"}]}"
        )
    }

    @Test
    fun getDetailSeries() {
        val gson = Gson()
        val typeSeries = object : TypeToken<DetailSeriesResponse>() {}.type
        val dummySeries: DetailSeriesResponse = gson.fromJson(data, typeSeries)
        val series = MutableLiveData<DetailSeriesResponse>()
        series.value = dummySeries

        Mockito.`when`(seriesRepository.getDetailSeries("1222585")).thenReturn(series)
        val list = viewModel.getDetailSeries(1222585).value
        verify(seriesRepository).getDetailSeries("1222585")
        viewModel.getDetailSeries(1222585).observeForever(observer)
        Mockito.verify(observer).onChanged(dummySeries)

        Assert.assertNotNull(list)
        Assert.assertEquals(dummySeries.name, list?.name)
        Assert.assertEquals(dummySeries.adult, list?.adult)
        Assert.assertEquals(dummySeries.original_language, list?.original_language)
        Assert.assertEquals(dummySeries.vote_average, list?.vote_average)
        Assert.assertEquals(dummySeries.backdrop_path, list?.backdrop_path)
        Assert.assertEquals(dummySeries.first_air_date, list?.first_air_date)
        Assert.assertEquals(dummySeries.poster_path, list?.poster_path)
    }


    @Test
    fun getRelatedSeries() {
        val gson = Gson()
        val type = object : TypeToken<List<SeriesResponse>>() {}.type
        val dummy: List<SeriesResponse> = gson.fromJson(dataRelated, type)
        val series = MutableLiveData<List<SeriesResponse>>()
        series.postValue(dummy)

        Mockito.`when`(seriesRepository.getListSeriesByGenre("10759")).thenReturn(series)
        val result = viewModel.getRelatedSeries("10759").value
        verify(seriesRepository).getListSeriesByGenre("10759")
        viewModel.getRelatedSeries("10759").observeForever(observerList)
        Mockito.verify(observerList).onChanged(series.value)

        Assert.assertNotNull(result)
        Assert.assertEquals(series.value?.size, result?.size)
    }

    @Test
    fun addToFav() {
        val gson = Gson()
        val type = object : TypeToken<DetailSeriesResponse>() {}.type
        val dummy: DetailSeriesResponse = gson.fromJson(data, type)
        val series = MutableLiveData<Map<String, Any>>()
        series.postValue(mutableMapOf("status" to true, "message" to ""))

        Mockito.`when`(seriesRepository.addFav(dummy)).thenReturn(series)
        val result = viewModel.addToFav(dummy).value
        verify(seriesRepository).addFav(dummy)
        viewModel.addToFav(dummy).observeForever(observerMap)
        Mockito.verify(observerMap).onChanged(series.value)
        Assert.assertNotNull(result)
        Assert.assertEquals(result?.get("status"), true)
    }

    @Test
    fun deleteFav() {
        val gson = Gson()
        val type = object : TypeToken<DetailSeriesResponse>() {}.type
        val dummy: DetailSeriesResponse = gson.fromJson(data, type)
        val series = MutableLiveData<Map<String, Any>>()
        series.postValue(mutableMapOf("status" to true, "message" to ""))

        runBlocking {
            Mockito.`when`(seriesRepository.deleteFavById(eq("s-${dummy.id}"))).thenReturn(series)
        }
        val result = viewModel.deleteById("s-${dummy.id}").value
        verify(seriesRepository).deleteFavById("s-${dummy.id}")
        viewModel.deleteById("s-${dummy.id}").observeForever(observerMap)
        Mockito.verify(observerMap).onChanged(series.value)
        Assert.assertNotNull(result)
        Assert.assertEquals(result?.get("status"), true)
    }

    @Test
    fun checkIsFav() {
        val series = MutableLiveData<FavEntity>()
        series.postValue(seriesFavEntity)

        runBlocking {
            Mockito.`when`(seriesRepository.getFavById(eq(seriesFavEntity.id))).thenReturn(series)
        }
        val result = viewModel.checkIsFav(seriesFavEntity.id).value
        verify(seriesRepository).getFavById(seriesFavEntity.id)
        viewModel.checkIsFav(seriesFavEntity.id).observeForever(observerFav)
        Mockito.verify(observerFav).onChanged(seriesFavEntity)
        Assert.assertNotNull(result)
        Assert.assertEquals(seriesFavEntity, result)
    }

}