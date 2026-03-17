package com.example;

import org.springframework.asm.*;

public class String_Short {
    public static byte[] cleanBytecode(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM9, cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                return new MethodVisitor(Opcodes.ASM9, mv) {
                    @Override
                    public void visitLineNumber(int line, Label start) {
                        // Skip line numbers
                    }
                };
            }
        };
        cr.accept(cv, ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);
        return cw.toByteArray();
    }
}
