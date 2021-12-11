package com.katafoni.kindlevocabulary.language;

import com.katafoni.kindlevocabulary.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface LanguageRepository extends JpaRepository<Language, Long>{
    Optional<Language> findByAbbreviationIgnoreCase(String abbreviation);
    Optional<Language> findByLanguageNameIgnoreCase(String languageName);
}
