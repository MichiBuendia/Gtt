package org.engim.gtt;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
import org.engim.gtt.database.Fermata;
import org.engim.gtt.database.Linea;
import org.engim.gtt.database.LineaFermata;
import org.engim.gtt.database.Mezzo;

public class Fermate extends WebPage
{
  public Fermate()
  {
    BookmarkablePageLink home =
      new BookmarkablePageLink("linkhome", 
              HomePage.class);
    add(home);
    
    List<IColumn<Fermata, String>> colonne =
      new LinkedList<>();
    
    PropertyColumn<Fermata, String> id =
      new PropertyColumn<>(Model.of("ID"), 
                            "id");
    colonne.add(id);
    
    PropertyColumn<Fermata, String> cod =
      new PropertyColumn<>(Model.of("Cod"), 
                            "codice");
    colonne.add(cod);

    PropertyColumn<Fermata, String> lat =
      new PropertyColumn<>(Model.of("Lat"), 
                            "latitudine");
    colonne.add(lat);

    PropertyColumn<Fermata, String> lng =
      new PropertyColumn<>(Model.of("Lng"), 
                            "longitudine");
    colonne.add(lng);
    
    AbstractColumn<Fermata, String> vel =
      new AbstractColumn<Fermata, String>(
                                  Model.of("Il più veloce"))
    {
      @Override
      public void populateItem(
                    Item<ICellPopulator<Fermata>> 
                            item, 
                    String wicketid, 
                    IModel<Fermata> imodel)
      {
        Mezzo veloce = trovaMezzoPiùVeloce(
                imodel.getObject());
        Label lbl = new Label(wicketid,
                              veloce.getTipo());
        item.add(lbl);
      }
    };
    
    colonne.add(vel);

    SPDataProvider<Fermata> dp = 
            new SPDataProvider<>(Fermata.class);
    
    DefaultDataTable tab =
      new DefaultDataTable("fermate",
                           colonne,
                           dp,
                           10);
    add(tab);
  }
  
  private Mezzo trovaMezzoPiùVeloce(
                              Fermata f)
  {
    EntityManager db = PM.db();
    try
    {
      TypedQuery<LineaFermata> linee =
        db.createNamedQuery(
                "LineaFermata.findByFermata", 
                LineaFermata.class);
      linee.setParameter("fermata", f);
      List<LineaFermata> results =
              linee.getResultList();

      Mezzo veloce = null;
      for (LineaFermata lf: results)
      {
        Linea l = lf.getLinea();
        Mezzo m = trovaMezzoDaLinea(l);
        if (veloce == null ||
            m.getVelocitàMax().
                compareTo(
                  veloce.
                    getVelocitàMax()) > 0
           )
          veloce = m;
      }
      return veloce;
    }
    finally
    {
      db.close();
    }
  }
  
  private Mezzo trovaMezzoDaLinea(
                            Linea l)
  {
    EntityManager db = PM.db();
    try
    {
      TypedQuery<Mezzo> q =
        db.createNamedQuery(
                "Mezzo.findByTipo", 
                Mezzo.class);
      q.setParameter("tipo", l.getTipo());
      return q.getSingleResult();
    }
    finally
    {
      db.close();
    }
  }
}
