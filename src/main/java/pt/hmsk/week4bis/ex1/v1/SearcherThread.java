package pt.hmsk.week4bis.ex1.v1;


class SearcherThread extends Thread {
    private TextRepository textRepository;

    public SearcherThread(TextRepository textRepository, int id) {
        super("[Search Thread - " + id +"]");
        this.textRepository = textRepository;
    }

    @Override
    public void run() {
        TextChunk chunk;
        // TODO while there are chunks, remove a chunk
        while ((chunk = textRepository.getChunk()) != null) {
            // TODO process the chunk
            processChunk(chunk);
        }
        // TODO print all found positions
    }

    private void processChunk(TextChunk chunk) {
        int fromIndex = 0;
        while (fromIndex < chunk.text.length()) {
            int foundPos = chunk.text.indexOf(chunk.stringToBeFound, fromIndex);
            if(foundPos == -1) {
                break;
            }
            System.out.println(getName() + " found text at " + (foundPos + chunk.getInitialPos()));
            fromIndex = foundPos + chunk.stringToBeFound.length();
        }
        
    }
}
