package com.katafoni.kindlevocabulary.dto.intern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KindleWord {

    private String id;

    private String word;

    private String stem;

    private String language;

    private String timestamp;
}
