package HTMLeditor.actions;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

public class StrikeThroughAction extends StyledEditorKit.StyledTextAction {

    public StrikeThroughAction() {
        super(StyleConstants.StrikeThrough.toString());
    }

    public void actionPerformed(ActionEvent actionEvent) {
        JEditorPane editor = getEditor(actionEvent);
        if (editor != null) {
            // связываем область редактора editor с областью редактирования StyledEditorKit и получаем входные атрибуты (текст) )с помощью метода  getInputAttributes.
            MutableAttributeSet mutableAttributeSet = getStyledEditorKit(editor).getInputAttributes();
            SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet(); // Создание набора атрибутов. SimpleAttributesSet - простой стиль, определяющий множество атрибутов
            // проверяем какие атрибуты (текст) не зачеркнуты (!StyleConstants.isStrikeThrough(mutableAttributeSet)) в mutableAttributeSet и зачекнув его добавляем в simpleAttributeSet
            StyleConstants.setStrikeThrough(simpleAttributeSet, !StyleConstants.isStrikeThrough(mutableAttributeSet));
            setCharacterAttributes(editor, simpleAttributeSet, false); //заменяем в editor не зачеркнутые атрибуты (текст) на зачеркнутые из simpleAttributeSet

        }
    }
}
