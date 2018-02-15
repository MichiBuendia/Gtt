package org.engim.gtt;

import java.util.LinkedList;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.engim.gtt.database.Linea;

public class Linee extends WebPage
{
  public Linee()
  {
    BookmarkablePageLink home =
      new BookmarkablePageLink("linkhome", 
              HomePage.class);
    add(home);
    
    List<IColumn<Linea, String>>
            colonne = new LinkedList<>();
    
    PropertyColumn<Linea, String> cod =
      new PropertyColumn<>
            (Model.of("N°"),
                      "codice");
    colonne.add(cod);
    
    PropertyColumn<Linea, String> tipo =
      new PropertyColumn<>
            (Model.of("Tipo"),
                      "tipo");
    colonne.add(tipo);
    
    AbstractColumn<Linea, String> calc =
      new AbstractColumn<Linea, String>(
               Model.of("Casuale"))
      {
        @Override
        public void populateItem(
                Item<ICellPopulator<Linea>> item, 
                String wicketid, 
                IModel<Linea> imodel)
        {
          double rand = Math.random();
          Label contenuto = new Label(
                            wicketid, 
                            "" + rand);
          item.add(contenuto);
                  
        }
      };
    
    colonne.add(calc);
    
    SPDataProvider<Linea> dp =
      new SPDataProvider<>(Linea.class);
    
    DefaultDataTable ddt = new
      DefaultDataTable("linee", 
              colonne, 
              dp, 10);
    add(ddt);     
  }
}
