package tool;

public enum Language {
    AFRIKAANS("Tiếng Afrika", "af"),
    ALBANIAN("Tiếng Albania", "sq"),
    ARABIC("Tiếng Ả Rập", "ar"),
    ARMENIAN("Tiếng Armenia", "hy"),
    AZERBAIJANI("Tiếng Azerbaijani", "az"),
    BASQUE("Tiếng Basque", "eu"),
    BELARUSIAN("Tiếng Belarus", "be"),
    BENGALI("Tiếng Bengali", "bn"),
    BULGARIAN("Tiếng Bungari", "bg"),
    CATALAN("Tiếng Catalan", "ca"),
    CHINESE_SIMPLIFIED("Tiếng Trung (giản thể)", "zh-CN"),
    CHINESE_TRADITIONAL("Tiếng Trung (phồn thể)", "zh-TW"),
    CROATIAN("Tiếng Croatia", "hr"),
    CZECH("Tiếng Séc", "cs"),
    DANISH("Tiếng Đan Mạch", "da"),
    DUTCH("Tiếng Hà Lan", "nl"),
    ENGLISH("Tiếng Anh", "en"),
    ESTONIAN("Tiếng Estonian", "et"),
    FILIPINO("Tiếng Philíp", "tl"),
    FINNISH("Tiếng Phần", "fi"),
    FRENCH("Tiếng Pháp", "fr"),
    GALICIAN("Tiếng Galicia", "gl"),
    GEORGIAN("Tiếng Georgian", "ka"),
    GERMAN("Tiếng Đức", "de"),
    GREEK("Tiếng Hy Lạp", "el"),
    GUJARATI("Tiếng Gujarati", "gu"),
    HAITIAN_CREOLE("Tiếng Haitian_Creole", "ht"),
    HEBREW("Tiếng Do Thái", "iw"),
    HINDI("Tiếng Hin-Ddi", "hi"),
    HUNGARIAN("Tiếng Hungar", "hu"),
    ICELANDIC("Tiếng Iceland", "is"),
    INDONESIAN("Tiếng Indones", "id"),
    IRISH("Tiếng Ailen", "ga"),
    ITALIAN("Tiếng Ý", "it"),
    JAPANESE("Tiếng Nhật", "ja"),
    KANNADA("Tiếng Kannada", "kn"),
    KOREAN("Tiếng Hàn", "ko"),
    LATIN("Tiếng Latin", "la"),
    LATVIAN("Tiếng Latvian", "lv"),
    LITHUANIAN("Tiếng Litva", "lt"),
    MACEDONIAN("Tiếng Macedonia", "mk"),
    MALAY("Tiếng Malay", "ms"),
    MALTESE("Tiếng Maltes", "mt"),
    NORWEGIAN("Tiếng Na Uy", "no"),
    PERSIAN("Tiếng Ba Tư", "fa"),
    POLISH("Tiếng Ba Lan", "pl"),
    PORTUGUESE("Tiếng Bồ Đào Nha", "pt"),
    ROMANIAN("Tiếng Rumani", "ro"),
    RUSSIAN("Tiếng Nga", "ru"),
    SERBIAN("Tiếng Serbia", "sr"),
    SLOVAK("Tiếng Slovak", "sk"),
    SLOVENIAN("Tiếng Sloven", "sl"),
    SPANISH("Tiếng Tây Ban Nha", "es"),
    SWAHILI("Tiếng Swahili", "sw"),
    SWEDISH("Tiếng Thụy Điển", "sv"),
    TAMIL("Tiếng Tamil", "ta"),
    TELUGU("Tiếng Telugu", "te"),
    THAI("Tiếng Thái Lan", "th"),
    TURKISH("Tiếng Thổ Nhĩ Kì", "tr"),
    UKRAINIAN("Tiếng Ukrain", "uk"),
    URDU("Tiếng U Đu", "ur"),
    VIETNAMESE("Tiếng Việt", "vi"),
    WELSH("Tiếng Wales", "cy"),
    YIDDISH("Tiếng Yiddish", "yi");

    private final String name;
    private final String code;

    private Language(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * Get the name of the language.
     * @return name of the language.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the code of the language.
     * @return code of the language.
     */
    public String getCode() {
        return code;
    }
}








































