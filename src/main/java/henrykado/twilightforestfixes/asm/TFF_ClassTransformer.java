package henrykado.twilightforestfixes.asm;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.util.TraceClassVisitor;

import net.minecraft.launchwrapper.IClassTransformer;

public class TFF_ClassTransformer implements IClassTransformer, Opcodes {
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {		
		if ("twilightforest.item.RegisterItemEvent".equals(transformedName))
		{
			ClassNode classNode = new ClassNode();
			new ClassReader(basicClass).accept(classNode, ClassReader.SKIP_FRAMES);
			
			for(MethodNode method : classNode.methods) 
			{
				if ("registerWoodVariants".equals(method.name))
				{
					for (AbstractInsnNode node : method.instructions.toArray())
					{
						if (node.getOpcode() == NEW && "net/minecraft/item/ItemDoor".equals(((TypeInsnNode)node).desc))
						{
							((TypeInsnNode)node).desc = "henrykado/twilightforestfixes/asm/ItemTFDoor";
						}
						else if (node.getOpcode() == INVOKESPECIAL && "net/minecraft/item/ItemDoor".equals(((MethodInsnNode)node).owner))
						{
							((MethodInsnNode)node).owner = "henrykado/twilightforestfixes/asm/ItemTFDoor";
							break;
						}
						
					}
					break;
				}
			}
			
			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			classNode.accept(writer);
			return writer.toByteArray();
		}
		
		return basicClass;
	}
}
