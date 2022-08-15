package com.firsttoy.redbeardandbread.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SingleResponseDto<T>  {

    private T response;

}
