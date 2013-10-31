package vivadaylight3.myrmecology.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.Icon;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import vivadaylight3.myrmecology.client.gui.content.AntBookText;
import vivadaylight3.myrmecology.common.lib.Resources;

public class GuiAntBook extends GuiScreen {
        
    String[] bookContent = new AntBookText().getContent();
        
    private int numPages = bookContent.length;
    private int currPage;
    private int updateCount = 0;
    private int bookImageWidth = 192;
    private int bookImageHeight = 192;
    
    private GuiButtonNextPage buttonNextPage;
    private GuiButtonNextPage buttonPreviousPage;

    public GuiAntBook() {
	
	//bookContent.add(new ContentPage1(this));
		
    }

    public void updateScreen()
    {
        super.updateScreen();
        ++this.updateCount;
    }
    
    public void initGui()
    {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);

        int i = (this.width - this.bookImageWidth) / 2;
        byte b0 = 2;
        this.buttonList.add(this.buttonNextPage = new GuiButtonNextPage(1, i + 120, b0 + 154, true));
        this.buttonList.add(this.buttonPreviousPage = new GuiButtonNextPage(2, i + 38, b0 + 154, false));
        this.updateButtons();
    }
    
    private void updateButtons()
    {
        this.buttonNextPage.drawButton = (this.currPage < this.numPages - 1);
        this.buttonPreviousPage.drawButton = this.currPage > 0;
    }
    
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        if (par1GuiButton.id == 1)
            {
                    ++this.currPage; 
            }
            else if (par1GuiButton.id == 2)
            {

                    --this.currPage;            
             }

            this.updateButtons();
    }
    
    private void drawIcon(Icon icon, int posX, int posY) {

	drawTexturedModelRectFromIcon(posX, posY, icon, 16, 16);

    }
    
    public void drawScreen(int par1, int par2, float par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Resources.GUI_ANTBOOK);
        int k = (this.width - this.bookImageWidth) / 2;
        byte b0 = 2;
        this.drawTexturedModalRect(k, b0, 0, 0, this.bookImageWidth, this.bookImageHeight);
        String s;
        String s1;
        int l;

        s = String.format(I18n.getString("book.pageIndicator"), new Object[] {Integer.valueOf(this.currPage + 1), Integer.valueOf(this.numPages)});
        l = this.fontRenderer.getStringWidth(s);
        this.fontRenderer.drawString(s, k - l + this.bookImageWidth - 44, b0 + 16, 0);
        this.fontRenderer.drawSplitString(bookContent[currPage], k + 36, b0 + 16 + 16, 116, 0);
        
        super.drawScreen(par1, par2, par3);
    }
    
}
