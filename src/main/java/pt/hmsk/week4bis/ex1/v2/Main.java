package pt.hmsk.week4bis.ex1.v2;


public class Main {
    static final int NUM_THREADS = 10;
    static final int STRING_LENGTH = 1024001;
    static final int CHUNK_LENGTH = 1024;
    static final String STRING_TO_BE_FOUND = "caroline";

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 1000; ++j) {
            SearcherThread[] threads = new SearcherThread[NUM_THREADS];

            // Gera um 'documento de texto' e carrega-o no repositorio 
            RandomString rs = new RandomString(STRING_LENGTH);
            String text = rs.nextString();
            TextRepository textRepository = new TextRepository(text, STRING_TO_BE_FOUND, CHUNK_LENGTH);

            // Cria um vetor de threads para procurar o texto
            for (int i = 0; i != NUM_THREADS; i++) {
                threads[i] = new SearcherThread(textRepository, i);
                threads[i].start();
            }

            System.out.println(textRepository.getAllMatches());
        }
        

    }
}
