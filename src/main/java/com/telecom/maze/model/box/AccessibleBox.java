package com.telecom.maze.model.box;

/**
 * Cette interface permet d'indiquer qu'une boite I n'est pas isolée. Autrement dit si on considère qu'une boite I' à
 * la boite I comme voisin de droite alors il est possible de se rendre de la boite I' à la boite I. Si l'on considère
 * ces deux boites comme étant deux noeuds d'un graphe, on pourrait dire qu'il existe une arrête entre ces noeuds.
 */
public interface AccessibleBox {
}
