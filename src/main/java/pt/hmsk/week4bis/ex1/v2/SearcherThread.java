package pt.hmsk.week4bis.ex1.v2;


class SearcherThread extends Thread {
    private TextRepository textRepository;

    public SearcherThread(TextRepository textRepository, int id) {
        super("[Search Thread - " + id +"]");
        this.textRepository = textRepository;
    }

    @Override
    public void run() {
        TextChunk chunk;
        while ((chunk = textRepository.getChunk()) != null) {
            processChunk(chunk);
            textRepository.submitResult(chunk);
        }
    }

    private void processChunk(TextChunk chunk) {
        int fromIndex = 0;
        while (fromIndex < chunk.text.length()) {
            int foundPos = chunk.text.indexOf(chunk.stringToBeFound, fromIndex);
            if(foundPos == -1) {
                break;
            }
            chunk.addFoundPos(chunk.getInitialPos() + foundPos);
            fromIndex = foundPos + chunk.stringToBeFound.length();
        }
    }
}
