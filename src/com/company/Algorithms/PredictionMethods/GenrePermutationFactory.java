//    private List<List<Movie.GENRE>> getAllPermutations(List<Movie.GENRE> allgenres) {
//        List<List<Movie.GENRE>> allGenrePerms = new ArrayList<>();
//        List<Movie.GENRE> genrePermutation;
//        int totalNumGenres = allgenres.size();
//        GenrePermutationFactory genrePermutationFactory;
//
//
//        for (int numGenresInPerm = 1; numGenresInPerm < totalNumGenres; ++numGenresInPerm) {
//            genrePermutationFactory = new GenrePermutationFactory(numGenresInPerm, allgenres);
//            try {
//                while (true) {
//                    genrePermutation = genrePermutationFactory.getNextPermOfGenres();
//                    allGenrePerms.add(genrePermutation);
//                }
//
//            } catch (Exception ex) {
//
//            }
//        }
//
//        return allGenrePerms;
//
//    }

//class GenrePermutationFactory {
//
//    List<Integer> genrePermIndices;
//    List<Movie.GENRE> allGenres;
//
//    int nextIndexToIterate;
//    int numGenresInPerm;
//
//    public GenrePermutationFactory(int numGenresInPerm, List<Movie.GENRE> allGenres) {
//        genrePermIndices = new ArrayList<>();
//        nextIndexToIterate = numGenresInPerm;
//        this.allGenres = allGenres;
//        this.numGenresInPerm = numGenresInPerm;
//        for (int index = 0; index < numGenresInPerm; ++index) {
//            genrePermIndices.add(index);
//        }
//    }
//
//    public List<Movie.GENRE> getNextPermOfGenres() throws Exception {
//        while (existsDuplicate()) {
//            iterateIndices();
//        }
//
//        return getGenres(genrePermIndices);
//    }
//
//    public List<Movie.GENRE> getGenres(List<Integer> genrePermIndices) {
//        return genrePermIndices.stream().map(genreIndex -> allGenres.get(genreIndex)).collect(Collectors.toList());
//    }
//
//    private void iterateIndices() throws Exception {
//        if (genrePermIndices.get(nextIndexToIterate) == numGenresInPerm) {
//            if (nextIndexToIterate == 0) {
//                throw new Exception();
//            }
//
//            genrePermIndices.set(nextIndexToIterate--, 0);
//            iterateIndices();
//        }
//
//        genrePermIndices.set(nextIndexToIterate, genrePermIndices.get(nextIndexToIterate) + 1);
//    }
//
//    private boolean existsDuplicate() {
//        for (int index = 0; index < numGenresInPerm; ++index) {
//            if (Collections.frequency(genrePermIndices, index) > 1) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//}