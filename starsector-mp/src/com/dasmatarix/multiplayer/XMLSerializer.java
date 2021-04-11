
package com.dasmatarix.multiplayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fs.starfarer.campaign.fleet.CampaignFleet;
import com.fs.starfarer.campaign.fleet.CampaignFleetView;
import com.fs.starfarer.campaign.fleet.CargoData;
import com.fs.starfarer.campaign.fleet.FleetData;
import com.fs.starfarer.campaign.fleet.FleetMember;
import com.fs.starfarer.campaign.fleet.LogisticsModule;
import com.fs.starfarer.campaign.save.CampaignGameManager;
import com.fs.starfarer.campaign.save.G;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * The Class XMLSerializer.
 */
public class XMLSerializer extends CustomSerializer {

	/** The x stream. */
	private static XStream xStream = createXStream();

	/**
	 * Gets the x stream.
	 *
	 * @return the x stream
	 */
	public static XStream createXStream() {
		XStream xStream;
		try {
			xStream = CampaignGameManager.Ò00000("0.3");
		} catch (NullPointerException npe) {
			xStream = new XStream(new StaxDriver());

			xStream.registerConverter(new XmlConverter());
			// fields that when I serialize are potential causes of a crash,
			// need to copy how alex serializes in save?
			xStream.omitField(CampaignFleet.class, "fleet");
			xStream.omitField(CampaignFleet.class, "noCombat");
			xStream.omitField(CampaignFleet.class, "movementModule");
			xStream.omitField(CampaignFleet.class, "facingModule");
			xStream.omitField(CampaignFleet.class, "ai");
			xStream.omitField(CampaignFleet.class, "interactionTarget");
			xStream.omitField(CampaignFleet.class, "accidentManager");
			xStream.omitField(CampaignFleet.class, "logistics");
			xStream.omitField(CampaignFleet.class, "sensorRangeIndicator");
			xStream.omitField(CampaignFleet.class, "battle");
			xStream.omitField(CampaignFleet.class, "noCombatPulse");
			xStream.omitField(CampaignFleet.class, "despawnListeners");
			xStream.omitField(CampaignFleet.class, "abilityRenderer");
			xStream.omitField(CampaignFleet.class, "inflater");
			xStream.omitField(CampaignFleet.class, "layers");
			xStream.omitField(CampaignFleet.class, "jumpDestination");

			xStream.omitField(CampaignFleetView.class, "fleet");

			xStream.omitField(FleetData.class, "fleet");
			xStream.omitField(FleetData.class, "commander");
			xStream.omitField(FleetData.class, "defaultCommander");
			xStream.omitField(FleetData.class, "iTO");
			xStream.omitField(FleetData.class, "snapshot");
			xStream.omitField(FleetData.class, "membersWithoutNull");
			xStream.omitField(FleetData.class, "sortedMembersWithoutNull");
			xStream.omitField(FleetData.class,
			        "sortedMembersWithoutNullWithFighters");
			xStream.omitField(FleetData.class, "nameSources");
			xStream.omitField(FleetData.class, "shipNameRandom");
			xStream.omitField(FleetData.class, "officers");
			xStream.omitField(FleetData.class, "cacheClearedOnSync");

			xStream.omitField(FleetMember.class, "fleetData");
			xStream.omitField(FleetMember.class, "fleetDataForStats");
			xStream.omitField(FleetMember.class, "captain");
			xStream.omitField(FleetMember.class, "fleetCommanderForStats");

			xStream.omitField(CargoData.class, "origSource");
			xStream.omitField(CargoData.class, "carryingFleet");
			xStream.omitField(CargoData.class, "mothballedShips");
			xStream.omitField(LogisticsModule.class, "fleet");

		}
		return xStream;
	}

	/**
	 * Deserialize.
	 *
	 * @param bytes the bytes
	 * @return the object
	 */
	@SuppressWarnings("rawtypes")
	public Object deserialize(byte[] compressedBytes , Class clazz) {
		byte[] bytes = GZipUtils.gzipUncompress(compressedBytes);
		return xStream.fromXML(new String(bytes));
	}

	/**
	 * Serialize.
	 *
	 * @param object the object
	 * @param clazz  the clazz
	 * @return the byte[]
	 * @throws ClassNotFoundException the class not found exception
	 */
	public byte[] serialize(Object object,
	        @SuppressWarnings("rawtypes") Class clazz)
	        throws ClassNotFoundException {
		// confirm this class has been registered
		if (classRegister.get(clazz.getName().hashCode()) == null) {
			throw new ClassNotFoundException(
			        "Did you register " + clazz.getName()
			                + " with the Serializer class register map?");
		}

		String s = (xStream.toXML(object));

		byte[] compressedBytes = GZipUtils.gzipCompress(s.getBytes());

		return compressedBytes;
	}

	/** The denied classes. */
	private static List<Class> deniedClasses = new ArrayList<Class>();

	/**
	 * Gets the denied classes.
	 *
	 * @return the denied classes
	 */
	public static List<Class> getDeniedClasses() {
		return deniedClasses;
	}

	/** The denied class names. */
	private static List<String> deniedClassNames = new ArrayList<String>();

	/**
	 * Gets the denied class names.
	 *
	 * @return the denied class names
	 */
	public static List<String> getDeniedClassNames() {
		return deniedClassNames;
	}

	/**
	 * The Class XmlConverter.
	 */
	public static class XmlConverter implements Converter {

		/**
		 * Can convert.
		 *
		 * @param param1Class the param 1 class
		 * @return true, if successful
		 */
		public boolean canConvert(Class param1Class) {
			if (param1Class.isArray())
				return false;
			ArrayList<Class> arrayList = new ArrayList(o00000(param1Class));
			for (Class clazz : arrayList) {
				java.lang.String str1 = clazz.getCanonicalName();
				java.lang.String str2 = (clazz.getPackage() != null)
				        ? clazz.getPackage().getName()
				        : null;
				if (clazz.isAnonymousClass() && str2 != null
				        && str2.startsWith("com.fs")
				        && !str2.startsWith("com.fs.starfarer.api")) {
					if (!Comparator.class.isAssignableFrom(clazz)
					        && !com.fs.util.DoNotObfuscate.class
					                .isAssignableFrom(clazz))
						XMLSerializer.getDeniedClasses().add(clazz);
					continue;
				}
				if (str1 != null && str1.startsWith("com.fs")
				        && !str1.startsWith("com.fs.starfarer.api")
				        && !com.fs.util.DoNotObfuscate.class
				                .isAssignableFrom(clazz))
					XMLSerializer.getDeniedClassNames().add(str1);
			}
			return false;
		}

		/**
		 * O 00000.
		 *
		 * @param param1Class the param 1 class
		 * @return the sets the
		 */
		private static Set<Class> o00000(Class param1Class) {
			HashSet hashSet = new HashSet();
			while (param1Class != null) {
				hashSet.add(param1Class);
				processInterfaces(param1Class, hashSet);
				param1Class = param1Class.getSuperclass();
			}
			return hashSet;
		}

		/**
		 * Process interfaces.
		 *
		 * @param param1Class the param 1 class
		 * @param param1Set   the param 1 set
		 */
		protected static void processInterfaces(Class param1Class,
		        Set param1Set) {
			param1Set.add(param1Class);
			Class[] arrayOfClass = param1Class.getInterfaces();
			for (byte b = 0; b < arrayOfClass.length; b++)
				processInterfaces(arrayOfClass[b], param1Set);
		}

		/**
		 * Marshal.
		 *
		 * @param paramObject                   the param object
		 * @param paramHierarchicalStreamWriter the param hierarchical stream
		 *                                      writer
		 * @param paramMarshallingContext       the param marshalling context
		 */
		public void marshal(java.lang.Object paramObject,
		        HierarchicalStreamWriter paramHierarchicalStreamWriter,
		        MarshallingContext paramMarshallingContext) {
		}

		/**
		 * Unmarshal.
		 *
		 * @param param1HierarchicalStreamReader the param 1 hierarchical stream
		 *                                       reader
		 * @param param1UnmarshallingContext     the param 1 unmarshalling
		 *                                       context
		 * @return the object
		 */
		public Object unmarshal(
		        HierarchicalStreamReader param1HierarchicalStreamReader,
		        UnmarshallingContext param1UnmarshallingContext) {
			return null;
		}
	}
}
