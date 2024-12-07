package com.demo.payload;

import lombok.Data;

@Data
public class FavouriteDto {

    private long id;
    private boolean isFavourite;
    private long appUserId;
    private long propertyId;

}
