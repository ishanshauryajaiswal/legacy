package com.example.playcompose.data.mapper

import com.example.playcompose.data.local.CompanyListingEntity
import com.example.playcompose.domain.model.CompanyListing


fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        symbol, name, exchange)
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity{
    return CompanyListingEntity(
        symbol, name, exchange)
}