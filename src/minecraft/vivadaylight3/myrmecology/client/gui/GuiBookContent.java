package vivadaylight3.myrmecology.client.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.Icon;

public class GuiBookContent {
    
    ArrayList<Object> contentList = new ArrayList<Object>();
    private GuiScreen gui;

    public GuiBookContent(GuiScreen parGui) {
	
	gui = parGui;
	
    }
    
    public void add(Icon icon){
	
	contentList.add(icon);
	
    }
    
    public void add(String string){
	
	contentList.add(string);
	
    }
    
    public void draw(FontRenderer render, int minX, int maxX, int y){
	
	int offset = 0;
	int genOffset = 0;
	
	for(int i = 0; i < contentList.size(); i++){
	    
	    if(contentList.get(i) instanceof String){
		
		offset = 0;
		
		List list = render.listFormattedStringToWidth(contentList.get(i).toString(), y);

	        for (Iterator iterator = list.iterator(); iterator.hasNext(); maxX += render.FONT_HEIGHT)
	        {
	            offset++;
	        }
		
	        render.drawSplitString((String) contentList.get(i), minX + (genOffset * render.FONT_HEIGHT), maxX + (genOffset * render.FONT_HEIGHT), 116, 0);
	        
	        genOffset += offset + 1;
	        
	    }else if(contentList.get(i) instanceof Icon){
		
		 gui.drawTexturedModelRectFromIcon(minX, y + (genOffset * 16), (Icon)contentList.get(i), 16, 16);
		
	    }
	    
	}
		
    }

}
