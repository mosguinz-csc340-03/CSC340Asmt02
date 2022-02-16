/**
 * Represents a query option that the user may provide when making a query.
 */
enum QueryOption implements QueryParameter {
    PART_OF_SPEECH {
        @Override
        public QueryOption parse(String s) {
            try {
                PartOfSpeech.parse(s);
                return this;
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        @Override
        public String toString() {
            return "a part of speech";
        }
    },
    DISTINCT,
    REVERSE;

    @Override
    public QueryOption parse(String s) {
        return this.name().equalsIgnoreCase(s) ? this : null;
    }

    @Override
    public String toString() {
        return String.format("'%s'", this.name().toLowerCase());
    }
}

interface QueryParameter {

    /**
     * @return A string representation of the parameter. Displayed to user as part of error
     *         message.
     */
    String toString();

    /**
     * Get the enum constant of the requested parameter type. Returns {@code null} if the method
     * fails to parse the given string.
     *
     * @return The enum constant that represents a query parameter. Returns {@code null} if the
     *         specified value cannot be parsed.
     */
    QueryParameter parse(String s);

}