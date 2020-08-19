package ru.daniels.treeinweb.dao;

import org.hibernate.SessionFactory;
import ru.daniels.treeinweb.models.Node;

import java.util.List;

/**
 * Интерефейс для работы с БД
 */
public interface Dao {

    public void setSessionFactory(SessionFactory sessionFactory);

    /**
     * создаем новую запись в БД
     * @param node новый объект
     * @return id нового объекта
     */
    public int create(Node node);

    /**
     * Обнавляем существующий объект в БД
     * @param node объект с внесенными изменениями
     */
    public void update(Node node);

    /**
     * Удаление объекта и его детей из БД
     * @param id id объекта для удаления
     */
    public void delete(int id);

    /**
     * получение списка обектов верхнего уровня
     * @return список объектов из БД
     */
    public List<Node> getParent();

    /**
     * Получение списка детей по id родителя
     * @param parentID id родителя
     * @return список детей данного родителя
     */

    public List<Node> getChildren(int parentID);



}
