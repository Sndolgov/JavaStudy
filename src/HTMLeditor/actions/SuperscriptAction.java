package HTMLeditor.actions;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

/**
 * Created by SergeyND on 24.05.2017.
 */
public class SuperscriptAction extends StyledEditorKit.StyledTextAction {


    public SuperscriptAction() {
        super(StyleConstants.Superscript.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JEditorPane editor = getEditor(e);
        if (editor != null) {
            // связываем область редактора editor с областью редактирования StyledEditorKit и получаем входные атрибуты (текст) )с помощью метода  getInputAttributes.
            MutableAttributeSet mutableAttributeSet = getStyledEditorKit(editor).getInputAttributes();
            SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet(); // Создание набора атрибутов. SimpleAttributesSet - простой стиль, определяющий множество атрибутов
            // проверяем какие атрибуты (текст) не зачеркнуты (!StyleConstants.isStrikeThrough(mutableAttributeSet)) в mutableAttributeSet и зачекнув его добавляем в simpleAttributeSet
            StyleConstants.setSuperscript(simpleAttributeSet, !StyleConstants.isSuperscript(mutableAttributeSet));
            setCharacterAttributes(editor, simpleAttributeSet, false); //заменяем в editor не зачеркнутые атрибуты (текст) на зачеркнутые из simpleAttributeSet

        }
    }
}
