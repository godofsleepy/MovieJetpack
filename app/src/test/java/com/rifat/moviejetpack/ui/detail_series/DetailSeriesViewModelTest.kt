package com.rifat.moviejetpack.ui.detail_series

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhaarman.mockitokotlin2.verify
import com.rifat.moviejetpack.data.entities.DetailSeriesEntity
import com.rifat.moviejetpack.data.repository.FakeSeriesRepository
import com.rifat.moviejetpack.data.repository.SeriesRepository
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

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var seriesRepository: SeriesRepository

    @Mock
    private lateinit var observer: Observer<DetailSeriesEntity>

    @Before
    fun setUp() {
        viewModel = DetailSeriesViewModel(seriesRepository)
        data =
            "{\"backdrop_path\":\"\\/h48Dpb7ljv8WQvVdyFWVLz64h4G.jpg\",\"created_by\":[{\"id\":1222585,\"credit_id\":\"55fdc50ec3a368132a001852\",\"name\":\"Tom Kapinos\",\"gender\":2,\"profile_path\":\"\\/ol7GfeO0OIDCWGYzlg1LDLmwluO.jpg\"}],\"episode_run_time\":[45],\"first_air_date\":\"2016-01-25\",\"genres\":[{\"id\":80,\"name\":\"Crime\"},{\"id\":10765,\"name\":\"Sci-Fi & Fantasy\"}],\"homepage\":\"https:\\/\\/www.netflix.com\\/title\\/80057918\",\"id\":63174,\"in_production\":true,\"languages\":[\"en\"],\"last_air_date\":\"2021-05-28\",\"last_episode_to_air\":{\"air_date\":\"2021-05-28\",\"episode_number\":16,\"id\":2856945,\"name\":\"A Chance at a Happy Ending\",\"overview\":\"The end is nigh! Lucifer, Chloe, Maze and Amenadiel prepare for battle with Michael and his not-so-angelic army of supporters.\",\"production_code\":\"\",\"season_number\":5,\"still_path\":\"\\/cYY0U8DAkCRAWO6rnIcZ2gW17Fz.jpg\",\"vote_average\":10,\"vote_count\":1},\"name\":\"Lucifer\",\"next_episode_to_air\":{\"air_date\":\"2022-01-10\",\"episode_number\":1,\"id\":2910283,\"name\":\"Nothing Ever Changes Around Here\",\"overview\":\"\",\"production_code\":\"\",\"season_number\":6,\"still_path\":null,\"vote_average\":0,\"vote_count\":0},\"networks\":[{\"name\":\"FOX\",\"id\":19,\"logo_path\":\"\\/1DSpHrWyOORkL9N2QHX7Adt31mQ.png\",\"origin_country\":\"US\"},{\"name\":\"Netflix\",\"id\":213,\"logo_path\":\"\\/wwemzKWzjKYJFfCeiB57q3r4Bcm.png\",\"origin_country\":\"\"}],\"number_of_episodes\":93,\"number_of_seasons\":6,\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Lucifer\",\"overview\":\"Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals.\\u00a0But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.\",\"popularity\":1464.514,\"poster_path\":\"\\/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg\",\"production_companies\":[{\"id\":43346,\"logo_path\":null,\"name\":\"Fox Productions\",\"origin_country\":\"US\"},{\"id\":1957,\"logo_path\":\"\\/3T19XSr6yqaLNK8uJWFImPgRax0.png\",\"name\":\"Warner Bros. Television\",\"origin_country\":\"US\"},{\"id\":57542,\"logo_path\":null,\"name\":\"Aggressive Mediocrity\",\"origin_country\":\"US\"},{\"id\":9993,\"logo_path\":\"\\/2Tc1P3Ac8M479naPp1kYT3izLS5.png\",\"name\":\"DC Entertainment\",\"origin_country\":\"US\"},{\"id\":40041,\"logo_path\":\"\\/oP8TmVSh9DCP1yhR2yvjnKfMgbg.png\",\"name\":\"Jerry Bruckheimer Television\",\"origin_country\":\"US\"}],\"production_countries\":[{\"iso_3166_1\":\"US\",\"name\":\"United States of America\"}],\"seasons\":[{\"air_date\":\"2015-07-10\",\"episode_count\":4,\"id\":70781,\"name\":\"Specials\",\"overview\":\"\",\"poster_path\":\"\\/bQ5FupU7DFTbx9pSgPsEZQwyZKj.jpg\",\"season_number\":0},{\"air_date\":\"2016-01-25\",\"episode_count\":13,\"id\":68415,\"name\":\"Season 1\",\"overview\":\"Bored with being the Lord of Hell, the devil relocates to Los Angeles, where he opens a nightclub and forms a connection with a homicide detective.\",\"poster_path\":\"\\/9qvNXKYqZEsYn3g3yn5tXQe0ceB.jpg\",\"season_number\":1},{\"air_date\":\"2016-09-19\",\"episode_count\":18,\"id\":78529,\"name\":\"Season 2\",\"overview\":\"Lucifer returns for another season, but his devil-may-care attitude may soon need an adjustment: His mother is coming to town.\",\"poster_path\":\"\\/fTQzbse8HKh0z6UJbMUumdbZ8PX.jpg\",\"season_number\":2},{\"air_date\":\"2017-10-02\",\"episode_count\":26,\"id\":91441,\"name\":\"Season 3\",\"overview\":\"As Lucifer struggles with an identity crisis, a gruff new police lieutenant shakes up the status quo with Chloe and the rest of the LAPD.\",\"poster_path\":\"\\/4mKbrTqGg1daz3pDUgqd9ZSdZRt.jpg\",\"season_number\":3},{\"air_date\":\"2019-05-08\",\"episode_count\":10,\"id\":117593,\"name\":\"Season 4\",\"overview\":\"As Chloe struggles to come to terms with Lucifer's disturbing revelation, a rogue priest sets out to stop a long-rumored prophecy.\",\"poster_path\":\"\\/k9sLJE5geAmOCXOCbKnhSNND60J.jpg\",\"season_number\":4},{\"air_date\":\"2020-08-21\",\"episode_count\":16,\"id\":152759,\"name\":\"Season 5\",\"overview\":\"Lucifer makes a tumultuous return to the land of the living in hopes of making things right with Chloe. A devil\\u2019s work is never done.\",\"poster_path\":\"\\/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg\",\"season_number\":5},{\"air_date\":\"2022-01-10\",\"episode_count\":10,\"id\":192616,\"name\":\"Season 6\",\"overview\":\"\",\"poster_path\":null,\"season_number\":6}],\"spoken_languages\":[{\"english_name\":\"English\",\"iso_639_1\":\"en\",\"name\":\"English\"}],\"status\":\"Returning Series\",\"tagline\":\"It's good to be bad.\",\"type\":\"Scripted\",\"vote_average\":8.5,\"vote_count\":9149}"
    }

    @Test
    fun getDetailSeries() {
        val gson = Gson()
        val typeSeries = object : TypeToken<DetailSeriesEntity>() {}.type
        val dummySeries: DetailSeriesEntity = gson.fromJson(data, typeSeries)
        val series = MutableLiveData<DetailSeriesEntity>()
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

}