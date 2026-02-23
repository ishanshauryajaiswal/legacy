package com.example.playcompose.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface StockDao {

    suspend fun insertCompanyListing(listing: List<CompanyListingEntity>)

    @Query("DELETE from companylistingentity")
    suspend fun clearCompanyListing()

    @Query(" SELECT * FROM companylistingentity WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR UPPER(:query) == symbol")
    suspend fun searchCompany(query: String): List<CompanyListingEntity>
}