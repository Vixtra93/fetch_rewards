package com.project.fetchrewards.viewmodels

import com.project.fetchrewards.remote.models.Item
import com.project.fetchrewards.repository.FetchRepository
import com.project.fetchrewards.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    @RelaxedMockK
    @MockK
    private lateinit var fetchRepository: FetchRepository

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when the API returns values`() = runBlocking {

        val myList = listOf<Item>(
            Item(1, 100, "Item 100"),
            Item(2, 102, "Item 102"),
            Item(2, 102, "Item 102"),
            Item(3, 103, "Item 103"),
            Item(3, 103, "Item 103"),
            Item(4, 104, "Item 104"),
            Item(4, 100, "Item 100"),
            Item(5, 105, "Item 105"),
        )
        val resourceResponse = Resource(Resource.Status.SUCCESS, myList, null)
        //Given
        coEvery { fetchRepository.getItems() } returns Resource.success(myList)


        //When
        val result = fetchRepository.getItems()


        //Then
        assert(resourceResponse == result)


    }

    @Test
    fun `When the api does not return values ​​or an error occurs`() = runBlocking {

        val resourceResponse = Resource(
            status = Resource.Status.ERROR,
            data = null,
            message = "Sorry an error occurred"
        )
        //Given
        coEvery { fetchRepository.getItems() } returns Resource.error("Sorry an error occurred")


        //When
        val result = fetchRepository.getItems()


        //Then
        assert(resourceResponse == result)

    }


}