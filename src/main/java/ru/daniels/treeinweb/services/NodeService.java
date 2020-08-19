package ru.daniels.treeinweb.services;


import ru.daniels.treeinweb.models.Node;

import java.util.List;


public interface NodeService {

    /**
     * Добавляем новый объект
     * @param node новый объект
     * @return id объекта
     */
    public int addNode(Node node);

    /**
     * Обновляем существующий объект
     * @param node измененный объект
     */
    public void updateNode(Node node);

    /**
     * Удаляем объект и его детей
     * @param id id удаляемого объекта
     */
    public void deleteNode(int id);

    /**
     * Получаем список объектов
     * @param id id родителя нужного объекта
     * @return список объектов объектов родителя
     */
    public List<Node> getListOfNodes(int id);
}
