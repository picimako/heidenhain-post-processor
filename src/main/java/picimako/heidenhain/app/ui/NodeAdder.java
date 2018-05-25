package picimako.heidenhain.app.ui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Helper class to be able to add a {@link Node} easier and in a more fluent way to {@link Pane} isntances.
 *
 * @author Tamas Balog
 */
public final class NodeAdder {
    private final Node[] nodes;

    private NodeAdder(Node[] nodes) {
        this.nodes = nodes;
    }

    /**
     * Creates a {@link NodeAdder} instance with the argument nodes.
     *
     * @param nodes the nodes to add
     * @return the {@code NodeAdder}
     */
    static NodeAdder add(Node... nodes) {
        return new NodeAdder(nodes);
    }

    /**
     * Add the preset nodes to the argument pane.
     *
     * @param pane the pane to add the nodes tos
     */
    void to(Pane pane) {
        for (Node node : nodes) {
            pane.getChildren().add(node);
        }
    }
}
