import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation

# define os vértices do triângulo
A = np.array([0, 0])
B = np.array([1, 0])
C = np.array([0.5, 0.8])

# cria a figura e o eixo
fig, ax = plt.subplots()

# desenha o triângulo
triangle = plt.Polygon([A, B, C], color='blue')
ax.add_patch(triangle)

# define a função de animação
def animate(i):
    # rotaciona o triângulo em torno do ponto A
    angle = np.radians(i)
    rotation_matrix = np.array([[np.cos(angle), -np.sin(angle)], [np.sin(angle), np.cos(angle)]])
    B_rotated = np.dot(rotation_matrix, B - A) + A
    C_rotated = np.dot(rotation_matrix, C - A) + A
    triangle.set_xy([A, B_rotated, C_rotated])
    return triangle,

# cria a animação
ani = animation.FuncAnimation(fig, animate, frames=360, interval=50, blit=True)

# mostra o gráfico animado
plt.axis('equal')
plt.show()
