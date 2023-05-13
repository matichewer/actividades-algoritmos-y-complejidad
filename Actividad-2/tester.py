import subprocess
import glob

# Encuentra todos los archivos de prueba en la carpeta actual
test_files = glob.glob("test-*.txt")
test_files = sorted(test_files)

for test_file in test_files:
    # Abre el archivo de prueba
    with open(test_file, 'r') as f:
        lines = f.readlines()

    # Separa las líneas en input y output esperado
    input_lines = []
    expected_output = []
    for line in lines:
        if line.strip() == '-':
            break
        input_lines.append(line)
    for line in lines[len(input_lines)+1:]:
        expected_output.append(line.strip())

    # Ejecuta el script de solución y envíale el input del archivo de prueba
    p = subprocess.Popen(['python', 'solucion2.py'], stdin=subprocess.PIPE, stdout=subprocess.PIPE)
    input_str = ''.join(input_lines)
    output_str = p.communicate(input=input_str.encode())[0].decode()

    # Compara la salida del script de solución con el output esperado
    if output_str.strip().split('\n') == expected_output:
        print(f"{test_file} superado exitosamente.")
    else:
        # imprime el mensaje de error si hay una discrepancia
        print("--------------------------------------------")
        print(f"Error en {test_file}\nOutput de la solucion:\n{output_str}")
        print("--------------------------------------------")
    
