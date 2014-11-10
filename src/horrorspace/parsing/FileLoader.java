package horrorspace.parsing;

import horrorspace.util.BufferedReaderFactory;
import horrorspace.util.LinebreakFileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Matt
 */
abstract class FileLoader<T> {
    private final BufferedReaderFactory readerFactory;
    private LinebreakFileReader linebreakFileReader;
    List<PlyElement> elements = new LinkedList<>();
    
    public FileLoader() {
        this(new BufferedReaderFactory());
    }
    
    public FileLoader(BufferedReaderFactory readerFactory) {
        this.readerFactory = readerFactory;
        
    }

    
    public T loadFile(String filename) throws IOException{
        linebreakFileReader = readerFactory.getReader(filename);
        readHeader();
        return parseContents();
    }

    private T parseContents() throws IOException {
        LoadableItemPrototype<T> prototype = getPrototype();
        for(PlyElement element : elements){
            element.process(linebreakFileReader, prototype);
        }
        return prototype.generateItem();
    }

    private void readHeader() throws IOException {
        checkTypeIsCorrect(linebreakFileReader);
        linebreakFileReader.readLine();
        while (!"end_header".equals(linebreakFileReader.getLastLine())) {            
            if(linebreakFileReader.getLastLine().contains("element")){
                pushElement();
            }else {
                linebreakFileReader.readLine();
            }
        }
    }

    private void pushElement() throws IOException {
        PlyElement element = createElement(linebreakFileReader.getLastLine());
        if(element == null)
            return;
        
        elements.add(element);
        
        //Add properties
        addProperties(element, linebreakFileReader);
    }
    
    protected abstract void checkTypeIsCorrect(LinebreakFileReader linebreakFileReader) throws IOException ;

    protected abstract PlyElement createElement(String lastLine);
    
    protected abstract void addProperties(PlyElement element, LinebreakFileReader linebreakFileReader) throws IOException;

    protected abstract LoadableItemPrototype<T> getPrototype();
}
