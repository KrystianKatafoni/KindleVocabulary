package com.katafoni.kindlevocabulary.vocabulary.transformation;

import java.util.Set;

interface DatabaseReader {
    Set<KindleWord> readWordsFromKindleDatabase(String databasePath);
}
